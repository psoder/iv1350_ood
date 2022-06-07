package view

import controller.Controller
import kotlin.system.exitProcess
import model.PriceWithVAT
import model.Sale
import java.util.Scanner

class View(val controller: Controller, val eol: String) {

    private fun listSale(sale: Sale): String {
        sale.priceStrategy = PriceWithVAT

        if (sale.items.any()) {
            return sale.items.fold("") { acc, (item, disc, qty) ->
                    acc.plus("${item.name}\t")
                            .plus("${item.price}\t")
                            .plus("${qty}\t")
                            .plus("${item.vat.rate}%\t")
                            .plus("${disc}%$eol")
                }
                .plus("----------------------------------------$eol")
                .plus("Total:\t${"%.2f".format(sale.price())} (of which ${"%.2f".format(sale.vat())} is VAT)$eol")
                .plus("")
        } else {
            return ""
        }
    }

    fun handleSale(scanner: Scanner = Scanner(System.`in`)) {
        while (true) {
            println("""
                |Register Balance: ${"%.2f".format(controller.registerBalance())}
                |
                |1. New Sale
                |2. Exit
                |
                """.trimMargin())

            when (scanner.nextLine()) {
                "1" -> {
                    controller.newSale()
                    while (true) {
                        println("""
                            |Item\tPrice\tQty\tVAT\tDiscount
                            |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                            |${listSale(controller.currentSale() ?: throw IllegalStateException("No ongoing sale"))}
                            |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                            |
                            |1. Enter item
                            |2. Apply discount
                            |3. Finnish sale
                            |
                            """.trimMargin())

                        try {
                            when (scanner.nextLine()) {
                                "1" -> {
                                    println("Enter item ID: (id [quantity])")
                                    // Hacky way to get the input for both parameters. It's good enough.
                                    val input = scanner.nextLine()!!.split(" ")
                                    val qty = input.getOrNull(1)?.toInt() ?: 1
                                    controller.enterItem(input[0], qty)
                                }
                                "2" -> {
                                    println("Enter customer ID: (id)")
                                    controller.applyDiscount(scanner.nextLine())
                                }
                                "3" -> {
                                    println("Enter amount paid: (amount)")
                                    controller.pay(scanner.nextLine().toDouble())
                                    break
                                }
                                else -> {
                                    println("Not a valid input")
                                }
                            }
                        } catch (e: Exception) {
                            println(e.message)
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
