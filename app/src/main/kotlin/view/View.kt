package view

import controller.Controller
import kotlin.system.exitProcess
import model.Sale

class View(val controller: Controller) {

    fun saleList(): String {
        val sale: Sale = controller.register.sale
            ?: throw IllegalStateException("No current sale")
        return sale.items
                .fold("") { acc, (item, disc, qty) ->
                    acc.plus("${item.name}\t")
                            .plus("${item.price}\t")
                            .plus("${qty}\t")
                            .plus("${item.vat.rate}%\t")
                            .plus("${disc}%\n")
                }
                .plus("Total:\t${"%.2f".format(sale.price())}")
    }

    fun handleSale() {
        while (true) {
            println("Register Balance: ${controller.register.balance}")

            println("1. New Sale")
            println("2. Exit")

            when (readLine()!!) {
                "1" -> {
                    controller.newSale()
                    while (true) {
                        println("\nItem\tPrice\tQty\tVAT\tDiscount")
                        println("${saleList()}\n")

                        println("1. Enter item")
                        println("2. Apply discount")
                        println("3. Finnish sale")

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
                            else -> {
                                println("Not a valid input")
                            }
                        }
                    }
                }
                "2" -> exitProcess(0)
                else -> {
                    println("Not a valid input")
                }
            }
        }
    }
}
