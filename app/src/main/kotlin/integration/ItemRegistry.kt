package integration

import kotlin.random.*

/**
 * Handles interaction with the products database (if there is one).
 */
class ItemRegistry {
    
    // HashMap<Item id, Pair<Item, quantity>>
    private val items = HashMap<String, Pair<Item, Int>>()

    // Populate the product map with products to simulate the diatabase.
    init {
        val products = listOf("Apple", "Banana", "Kiwi", "Orange", "Grape", "Pear")
        for (index in products.indices) {
            val price = Random.nextDouble(0.0, 20.0)
            val quantity = Random.nextInt(30, 100)
            items.put("$index", Pair(Item("$index", products[index], "%.2f".format(price).toDouble()), quantity))
        }
    }

    /**
     * Returns the item with corresponding id if an item with the id exists
     * in the database.
     * 
     * @param id the id of the item.
     * @return a nullable item.
     */
    fun getItem(id: String): Item? {
        return items[id]?.first      
    }

    /**
     * updates the inventory with the provided changes.
     * 
     * @param changes a map of the item ids as the key and the change as the value.
     */
    fun updateInventory(changes: Map<String, Int>) {
        for((id, change) in changes) {

        }
    }
}