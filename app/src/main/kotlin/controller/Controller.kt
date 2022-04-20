package controller

import integration.*
import model.Register

/** @author Pontus Söderlund */
class Controller (
    private val printer: Printer,
    private val itemRegistry: ItemRegistry,
    private val discountRegistry: DiscountRegistry,
    private val salesLog: SalesLog,
    private val accounting: Accounting
) {
    val register: Register = Register()

    /**
     * Starts a new transaction.
     */
    fun newTransaction() {
        register.newTransaction()
    }

    /**
     * Adds the given number of items to the cart if a item with the id exists
     *
     * @param id is the item id
     * @param quantity is how many to add
     */
    fun enterItem(id: String, quantity: Int = 1) {
        val item: Item = itemRegistry.getItem(id) 
            ?: throw NoSuchElementException("No item with id $id exists")
        register.enterItem(item, quantity)
    }
    

    /**
     * Applies a discount to the customer based on the customers id
     *
     * @param customerId is the id of the customer
     */
    fun applyDiscount(customerId: String) {
        val discounts: Map<String, Int> =
            discountRegistry.getDiscount(customerId)
            ?: throw NoSuchElementException("No customer with id $customerId exists")
        register.applyDiscount(discounts)
    }

    /**
     * Ends the current transaction and pays for the items and prints a receipt
     * for the transaction.
     *
     * @param amount is the amount the customer pays
     * @return a receipt of the transaction
     */
    fun pay(amount: Double): Receipt {
        val receipt = register.pay(amount)
        printer.print(receipt)
        salesLog.log(receipt)
        accounting.log(receipt)
        return receipt
    }
}
