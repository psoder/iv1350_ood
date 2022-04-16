package controller

import integration.*
import model.Register

/** @author Pontus Söderlund */
class Controller(
    val register: Register,
    val printer: Printer,
    val itemRegistry: ItemRegistry,
    val discountRegistry: DiscountRegistry,
    val salesLog: SalesLog,
    val accounting: Accounting
) {

    /**
     * Starts a new transaction.
     */
    fun newTransaction() {
        register.newTransaction()
    }

    /**
     * Adds an item to the cart if a item with the id exists
     *
     * @param ids is a list contianing the item ids
     */
    fun enterItem(ids: List<String>) {
        ids.forEach { id ->
            val item: Item = itemRegistry.getItem(id)
                ?: throw NoSuchElementException("No item with id $id exists")
            register.enterItem(item)
        }
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
