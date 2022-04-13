package integration

import kotlin.random.*

class DiscountRegistry {

    var discounts = HashMap<String, Map<String, Int>>()

    // Create a list of customers
    init {
        for (id in 1..10) {
            val disc = ArrayList<Pair<String, Int>>()
            for (i in 1..10) {
                disc.add(Pair("$i", Random.nextInt(0, 30)))
            }
            discounts.put("$id", disc.toMap())
        }
    }

    fun getDiscount(customerId: String): Map<String, Int>? {
        return discounts.get(customerId)
    }
}
