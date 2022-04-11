package view

import controller.Controller
import kotlin.system.exitProcess

class View(val controller: Controller) {

    fun transactionList(): String {
        val transaction = controller.register.transaction
        return transaction
                .items
                .groupBy { it.id }
                .asIterable()
                .fold("") { acc, (_, items) ->
                    acc + "${items[0].name} \t ${items.size} x ${items[0].price}\n"
                }
                .plus("Total: ${transaction.price()} ($transaction.discount% discount)")
    }

    fun handleTransaction() {
        while (true) {
            println("\nCart:")
            println("${transactionList()}\n")

            println("1. Enter item")
            println("2. Apply discount")
            println("3. Finnish transaction")
            println("4. Exit")

            when (readLine()!!) {
                "1" -> {
                    println("Enter item ID")
                    try {
                        controller.enterItem(readLine()!!)
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
                "2" -> {
                    println("Enter customer ID")
                    try {
                        controller.applyDiscount(readLine()!!)
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
                "3" -> {
                    println("Enter amount paid")
                    try {
                        controller.pay(readLine()!!.toDouble())
                    } catch (e: Exception) {
                        println(e.message)
                        continue
                    }
                    break
                }
                "4" -> exitProcess(0)
                else -> {
                    println("Not a valid input")
                }
            }
        }
    }
}
