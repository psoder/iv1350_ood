package main

import controller.*
import integration.*
import model.*
import view.*

fun main() {
    // Integration
    val printer = Printer()
    val itemRegistry = ItemRegistry()
    val customerRegistry = CustomerRegistry()
    val salesLog = SalesLog()

    // Model
    val register = Register(itemRegistry, customerRegistry, salesLog)

    // Controller
    val controller = Controller(register, printer)

    // View
    val view = View(controller)

    view.handleTransaction()
}
