package integration

import kotlin.random.*

class ItemRegistry {

    var items = HashMap<String, Pair<Item, Int>>()

    // Create a list of items
    init {
        val products = listOf("Apple", "Banana", "Kiwi", "Orange")
        for (index in products.indices) {
            val price = Random.nextDouble(0.0, 20.0)
            val quantity = Random.nextInt(30, 100)
            items.put("$index", Pair(Item("$index", products[index], "%.2f".format(price).toDouble()), quantity))
        }
    }

    fun getItem(itemId: String): Item? {
        return items.get(itemId)?.first      
    }

    fun updateInventory(changes: Map<String, Int>) {
        for((id, change) in changes) {

        }
    }
}

data class Item(
        val id: String,
        val name: String,
        val price: Double,
        val vat: Double = 6.0,
) {}
