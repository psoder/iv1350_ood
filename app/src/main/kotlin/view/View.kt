package view

import controller.Controller
import kotlin.system.exitProcess
import model.Sale
import model.PriceWithVAT

class View(val controller: Controller) {

    private val eol: String = System.getProperty("line.separator")

    fun saleList(): String {
        val sale: Sale = controller.register.sale
            ?: throw IllegalStateException("No current sale")
        
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
        }
        return ""
    }

    fun handleSale() {
        while (true) {
            println("Register Balance: ${"%.2f".format(controller.register.balance)}")

            println("1. New Sale")
            println("2. Exit")

            when (readLine()!!) {
                "1" -> {
                    controller.newSale()
                    while (true) {
                        println("${eol}Item\tPrice\tQty\tVAT\tDiscount")
                        println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
                        print("${saleList()}")
                        println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$eol")

                        println("1. Enter item")
                        println("2. Apply discount")
                        println("3. Finnish sale")

                        try {
                            when (readLine()!!) {
                                "1" -> {
                                    println("${eol}Enter item ID: (id [quantity])")
                                    // Hacky way to get the input for both parameters. It's good enough.
                                    val input = readLine()!!.split(" ")
                                    val qty = input.getOrNull(1)?.toInt() ?: 1
                                    controller.enterItem(input[0], qty)
                                }
                                "2" -> {
                                    println("${eol}Enter customer ID: (id)")
                                    controller.applyDiscount(readLine()!!)
                                }
                                "3" -> {
                                    println("${eol}Enter amount paid: (amount)")
                                    controller.pay(readLine()!!.toDouble())
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
