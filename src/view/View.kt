package view

import integration.Receipt
import controller.Controller

class View(val controller: Controller) {

    fun display(obj: Any): Unit {
        println(obj)
    }

    fun handleTransaction(): Unit {
        while (true) {
            println("Cart: \n")

            println("1. Enter item")
            println("2. Apply discount")
            println("3. Finnish transaction")
            
            when(readLine()!!) {
                "1" -> {
                    println("Enter item ID")
                    try {
                        controller.enterItem(readLine()!!)
                    }
                    catch(e: NoSuchElementException) {
                        println(e.message)
                    }
                }
                "2" -> {
                    println("Enter customer ID")
                    try {
                        controller.applyDiscount(readLine()!!)
                    }
                    catch(e: NoSuchElementException) {
                        println(e.message)
                    }
                }
                "3" -> {
                    println("Enter amount paid")
                    try {
                        controller.pay(readLine()!!.toDouble())
                    }
                    catch(e: NoSuchElementException) {
                        println(e.message)
                    }

                    break
                }
                else -> {
                    println("Not a valid input")
                }
            }
        }
    }
}