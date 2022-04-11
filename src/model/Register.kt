package model

import integration.*

class Register(
        val itemRegistry: ItemRegistry,
        val customerRegistry: CustomerRegistry,
        val salesLog: SalesLog,
        val seller: String = "Agent Smith",
        val place: String = "The Matrix",
) {
    var transaction = Transaction(place, seller)

    fun pay(amount: Double): Double {
        val price = transaction.price()
        require(amount >= price) { "You poor, $amount is less than $price" }
        return amount - price
    }

    fun enterItem(itemId: String) {
        val item = itemRegistry.getItem(itemId)
        transaction.addItem(item)
    }

    fun applyDiscount(customerId: String) {
        val discount = customerRegistry.getDiscount(customerId)
        transaction.applyDiscount(discount)
        transaction.customerId = customerId
    }
}
