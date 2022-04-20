package main

import controller.*
import integration.*
import model.*
import view.*

fun main() {
    // Integration
    val printer = Printer()
    val itemRegistry = ItemRegistry(listOf("Apple", "Banana", "Kiwi", "Orange", "Grape", "Pear"))
    val discountRegistry = DiscountRegistry(10)
    val salesLog = SalesLog()
    val accounting = Accounting()

    // Controller
    val controller = Controller(printer, itemRegistry, discountRegistry, salesLog, accounting)

    // View
    val view = View(controller)
    val revenueFile = TotalRevenueView()
    val revenueView = TotalRevenueFileOutput()
    controller.register.observers.add(revenueFile)
    controller.register.observers.add(revenueView)

    view.handleSale()
}
