package model

import integration.*

class Register(
        val printer: Printer,
        val itemRegistry: ItemRegistry,
        val customerRegistry: CustomerRegistry,
        val salesLog: SalesLog,
        val seller: String = "Agent Smith",
        val place: String = "The Matrix",
) {
    var transaction = Transaction(place, seller)
    var discount = 0

    fun pay(amount: Double): Unit {}

    fun enterItem(itemId: String): Unit {
        val item = itemRegistry.getItem(itemId)
        transaction.addItem(item)
    }

    fun applyDiscount(customerId: String): Unit {
        discount = customerRegistry.getDiscount(customerId)
        transaction.applyDiscount(discount)
    }

    fun printReceipt(): Unit {}
}
