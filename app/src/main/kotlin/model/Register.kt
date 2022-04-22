package model

import integration.*

/**
 * [Register] is the class that represents a cash register and contains logic for keeping track of
 * the current sale and balance.
 */
class Register {

    private val observers = ArrayList<RegisterObserver>()
    
    var balance = 0.0
        private set

    var sale: Sale? = null
        private set

    /**
     * Starts a new sale.
     *
     * @throws IllegalStateException if there's an active sale.
     */
    fun newSale() {
        sale?.let { throw IllegalStateException("Sale already in progress") }
        sale = Sale()
    }

    /**
     * Ends a sale and increments the registers balance.
     *
     * @param amount is the amount the customer pays.
     * @return a receipt of the sale.
     * @throws IllegalStateException if there is no current sale.
     */
    fun pay(amount: Double): Receipt {
        val s: Sale = sale ?: throw IllegalStateException("No current sale")
        val price = s.price() + s.vat()
        require(amount >= price) { "You poor, $amount is less than $price" }
        balance += price
        val receipt = s.getReceipt(amount)
        notifyObservers()
        sale = null
        return receipt
    }

    /**
     * Adds the given number of item to the current sale if it exists.
     *
     * @param itemId the id of the item to add.
     * @param quantity is the number of items to add.
     * @throws IllegalStateException if there is no current sale.
     * @throws IllegalArgumentException if the quantity is less than one.
     */
    fun enterItem(item: Item, quantity: Int = 1) {
        require(quantity > 0) { "Quantity ($quantity) must add a positive non-zero integer" }
        sale?.addItem(item, quantity)
                ?: throw IllegalStateException("No current sale")
    }

    /**
     * Applies any applicable discounts to the sale based on the customers id.
     *
     * @param discounts are the discounts to apply Map<Iten id, discount>.
     * @throws IllegalStateException if there is no current sale.
     */
    fun applyDiscount(discounts: Map<String, Int>) {
        sale?.applyDiscount(discounts)
                ?: throw IllegalStateException("No current sale")
    }

    /**
     * Adds an [RegisterObserver] to the observers. The observer is notified when the state changes.
     * 
     * @param obs is an object that implements the [RegisterObserver] interface.
     * @return The object the method was called on. 
     */
    fun addObserver(obs: RegisterObserver): Register {
        observers.add(obs)
        return this
    }
    
    private fun notifyObservers() = observers.forEach{it.balanceHasChanged(balance)}
}
