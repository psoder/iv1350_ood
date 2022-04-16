package integration

import kotlin.random.*

/**
 * Handles interaction with the discount database (if there is one).
 */
class DiscountRegistry {

    // HashMap<customer id, Map<Item id, percent discount>>
    private var discounts = HashMap<String, Map<String, Int>>()

    // Populate the discount map with discounts to simulate the diatabase.
    init {
        for (id in 1..10) {
            val disc = ArrayList<Pair<String, Int>>()
            for (i in 1..10) {
                disc.add(Pair("$i", Random.nextInt(0, 30)))
            }
            discounts.put("$id", disc.toMap())
        }
    }

    /**
     * If a customer with the specified id exists it returns a map containing
     * the discounts of the customer. If the id doesnt exist returns null.
     * 
     * @param customerId is the id of the customer.
     * @return a nullable map where the key is the item id and the value is
     * the discount.
     */
    fun getDiscount(customerId: String): Map<String, Int>? {
        return discounts[customerId]
    }
}
