package main

import view.*
import controller.*
import model.*
import integration.*
import java.util.Scanner

fun main() {
    // Integration 
    val printer = Printer()
    val itemRegistry = ItemRegistry()
    val customerRegistry = CustomerRegistry()
    val salesLog = SalesLog()

    // Model
    val register = Register(printer, itemRegistry, customerRegistry, salesLog)
    
    // Controller
    val controller = Controller(register)

    // View
    val view = View(controller)

    view.handleTransaction()
 }
