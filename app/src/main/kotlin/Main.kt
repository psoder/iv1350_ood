package main

import controller.*
import integration.*
import model.*
import view.*

fun main() {
    // Integration
    val printer = Printer()
    val itemRegistry = ItemRegistry()
    val discountRegistry = DiscountRegistry()
    val salesLog = SalesLog()
    val accounting = Accounting()

    // Model
    val register = Register()

    // Controller
    val controller = Controller(register, printer, itemRegistry, discountRegistry, salesLog, accounting)

    // View
    val view = View(controller)

    view.handleTransaction()
}
