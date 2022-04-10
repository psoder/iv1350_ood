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
            items.put("$index", Pair(Item("$index", products[index], price), quantity))
        }
    }

    fun getItem(itemId: String): Item {
        return items.get(itemId)?.first
                ?: throw NoSuchElementException("No item with id $itemId exists")
    }

    fun stockOf(item: Item): Int {
        return items.get(item.id)?.second ?: throw NoSuchElementException("$item does not exist")
    }
}

data class Item(
        val id: String,
        val name: String,
        var price: Double,
        val vat: Double = 6.0,
) {
    init {
        price = Math.round(price * 10) / 10.0
    }
}
