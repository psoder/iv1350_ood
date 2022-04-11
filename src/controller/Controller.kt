package controller

import integration.Printer
import model.Register
import model.Transaction

/** @author Pontus Söderlund */
class Controller(
        val register: Register,
        val printer: Printer,
) {

    /**
     * Adds an item to the cart if a item with the id exists
     *
     * @param itemId is the id of the item
     */
    fun enterItem(itemId: String) {
        register.enterItem(itemId)
    }

    /**
     * Applies a discount to the customer based on the customers id
     *
     * @param customerId is the id of the customer
     */
    fun applyDiscount(customerId: String) {
        register.applyDiscount(customerId)
    }

    /**
     * Pays and prints a receipt
     *
     * @param amount is the amount the customer pays
     * @return change
     */
    fun pay(amount: Double): Double {
        val change = register.pay(amount)
        register.transaction.amountPaid = amount
        printer.print(register.transaction.getReceipt())
        return change
    }
}
