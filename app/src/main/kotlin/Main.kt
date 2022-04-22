package main

import controller.*
import integration.*
import kotlin.random.Random
import model.*
import view.*

fun main() {
    // Integration
    Printer
    Accounting
    SalesLog

    // Populate the item registry with some items.
    ItemRegistry.let {
        val l = listOf("Apple", "Banana", "Kiwi", "Orange", "Grape", "Pear")
        l.forEachIndexed { index, name ->
            val item = Item("$index", name, "%.2f".format(Random.nextDouble(0.0, 20.0)).toDouble())
            ItemRegistry.addItem(item, Random.nextInt(30, 100))
        }
    }

    // Populate the discount registry with mock data.
    DiscountRegistry.let {
        for (id in 1..10) {
            val discount = ArrayList<Pair<String, Int>>()
            for (i in 1..100) {
                discount.add(Pair("$i", Random.nextInt(0, 30)))
            }
            DiscountRegistry.addDiscount("$id", discount.toMap())
        }
    }

    // Controller
    val controller = Controller()

    // View
    val view = View(controller)
    val revenueFile = TotalRevenueView()
    val revenueView = TotalRevenueFileOutput()
    controller.register
        .addObserver(revenueFile)
        .addObserver(revenueView)

    view.handleSale()
}