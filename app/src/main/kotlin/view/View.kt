package view

import controller.Controller
import model.Transaction
import kotlin.system.exitProcess

class View(val controller: Controller) {

    fun transactionList(): String {
        val transaction: Transaction = controller.register.transaction ?:
            throw IllegalStateException("No current transaction")
        return transaction.items
                .fold("") { acc, (item, disc, qty) ->
                    acc.plus("${item.name}\t")
                            .plus("${item.price}\t")
                            .plus("${qty}\t")
                            .plus("${item.vat.rate}%\t")
                            .plus("${disc}%\n")
                }
                .plus("Total:\t${"%.2f".format(transaction.price())}")
    }

    fun handleTransaction() {
        controller.newTransaction()
        while (true) {
            println("\nItem\tPrice\tQty\tVAT\tDiscount")
            println("${transactionList()}\n")

            println("1. Enter item")
            println("2. Apply discount")
            println("3. Finnish transaction")
            println("4. Exit")

            when (readLine()!!) {
                "1" -> {
                    println("\nEnter item ID")
                    try {
                        val input = readLine()!!.split(" ")
                        val qty = input.getOrNull(1)?.toInt() ?: 1
                        controller.enterItem(input[0], qty)
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
                "2" -> {
                    println("\nEnter customer ID")
                    try {
                        controller.applyDiscount(readLine()!!)
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
                "3" -> {
                    println("\nEnter amount paid")
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
