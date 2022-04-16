package model

import integration.*

/**
 * [Register] is the class that represents a cash register and contains logic for keeping track of
 * the current transaction and balance.
 */
class Register {

    var balance = 0.0
        private set

    var transaction: Transaction? = null
        private set

    /**
     * Starts a new transaction.
     * 
     * @throws IllegalStateException if there's an active transaction.
     */
    fun newTransaction() {
        transaction?.let { throw IllegalStateException("Transaction already in progress") }
        transaction = Transaction()
    }

    /**
     * Ends a transaction and increments the registers balance.
     *
     * @param amount is the amount the customer pays.
     * @return a receipt of the transaction .
     * @throws IllegalStateException if there is no current transaction.
     */
    fun pay(amount: Double): Receipt {
        val t: Transaction = transaction ?: throw IllegalStateException("No current transaction")
        val price = t.price()
        require(amount >= price) { "You poor, $amount is less than $price" }
        balance += price
        val receipt = t.getReceipt(amount)
        transaction = null
        return receipt
    }

    /**
     * Adds an item to the current transaction if it exists.
     *
     * @param itemId the id of the item to add.
     * @throws IllegalStateException if there is no current transaction.
     */
    fun enterItem(item: Item) {
        transaction?.addItem(item) ?: throw IllegalStateException("No current transaction")
    }

    /**
     * Applies any applicable discounts to the transaction based on the customers id.
     *
     * @param customerId the customers id.
     * @throws IllegalStateException if there is no current transaction.
     */
    fun applyDiscount(discounts: Map<String, Int>) {
        transaction?.applyDiscount(discounts)
                ?: throw IllegalStateException("No current transaction")
    }
}
