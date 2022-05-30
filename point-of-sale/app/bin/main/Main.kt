package main

import controller.*
import integration.*
import kotlin.random.Random
import model.*
import util.Logger
import view.*

fun main() {

    val eol: String = System.getProperty("line.separator")

    // Integration
    val discountRegistry = DiscountRegistry()
    val itemRegistry = ItemRegistry()

    // Populate the item registry with some items.
    discountRegistry.let {
        val l = listOf("Apple", "Banana", "Kiwi", "Orange", "Grape", "Pear")
        l.forEachIndexed { index, name ->
            val item = Item("$index", name, "%.2f".format(Random.nextDouble(0.0, 20.0)).toDouble())
            itemRegistry.addItem(item, Random.nextInt(30, 100))
        }
    }

    // Populate the discount registry with mock data.
    discountRegistry.let {
        for (id in 1..10) {
            val discount = ArrayList<Pair<String, Int>>()
            for (i in 1..100) {
                discount.add(Pair("$i", Random.nextInt(0, 30)))
            }
            discountRegistry.addDiscount("$id", discount.toMap())
        }
    }

    // Controller
    val controller =
            Controller(
                    printer = Printer(eol),
                    itemRegistry = itemRegistry,
                    discountRegistry = discountRegistry,
                    logger = Logger("controller.log", eol)
            )

    // View
    val view = View(controller, eol)
    val revenueFile = TotalRevenueView()
    val revenueView = TotalRevenueFileOutput("balance", eol)
    controller.register.addObserver(revenueFile).addObserver(revenueView)

    view.handleSale()
}
