package integration

import kotlin.random.*
import util.NoSuchServiceException

/** Handles interaction with the products database (if there is one). */
class ItemRegistry() {

    // HashMap<Item id, Pair<Item, quantity>>
    private var items: MutableMap<Item, Int> = mutableMapOf() 

    constructor(products: List<String>) : this() {
        // Populate the product map with products to simulate the diatabase.
        items = products
            .mapIndexed{ index, name -> Item("$index", name, "%.2f".format(Random.nextDouble(0.0, 20.0)).toDouble())}
            .associate { it to Random.nextInt(30, 100) }.toMutableMap()
    }
    
    /**
     * Creates an instance of this class with the provided items as inital state. 
     * 
     * @param products the items to use.
     */
    constructor(products: Map<Item, Int>) : this() {
        items = products.toMutableMap()
    }

    /**
     * Returns the item with corresponding id if an item with the id exists in the database.
     *
     * @param id the id of the item.
     * @return a nullable item.
     * @throws NoSuchServiceException if the DB is not running. 
     */
    fun getItem(id: String): Item? {
        if (id == "db not running") {throw NoSuchServiceException("DB not running")}
        return items.keys.find { item -> item.id.equals(id) }
    }

    /**
     * Adds an item into the inventory system.
     *
     * @param item the item to add
     * @param quantity the quantity
     * @throws IllegalArgumentException if an item with the given id
     */
    fun addItem(item: Item, quantity: Int) {
        require(quantity >= 0) { "Quantity must be 0 or more (was $quantity)" }
        if (items[item] == null) {
            items.put(item, quantity)
        } else {
            throw IllegalArgumentException("An item with id ${item.id} allready exists")
        }
    }

    /**
     * updates the inventory with the provided changes.
     *
     * @param changes a map of the item ids as the key and the change as the value.
     */
    fun updateInventory(changes: Map<String, Int>) {
        val v = items.filterKeys { changes.contains(it.id) }
        for ((item, value) in v) {
            val change = changes[item.id] ?: 0
            items.set(item, value + change)
        }
    }
}
