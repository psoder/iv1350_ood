package model

import integration.*

import integration.Item

class Register(
    val printer: Printer,
    val itemRegistry: ItemRegistry,
    val customerRegistry: CustomerRegistry,
    val salesLog: SalesLog
) {
    var items = ArrayList<Item>()
    var discount = 0
    
    fun pay(amount:Double): Unit {

    }

    fun enterItem(itemId: String): Unit {
        itemRegistry.getItem(itemId)
    }

    fun applyDiscount(customerId: String): Unit {
        discount = customerRegistry.getDiscount(customerId)
    }

    fun printReceipt(): Unit {
        printer.
    }
}