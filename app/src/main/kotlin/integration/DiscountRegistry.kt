package integration

/**
 * Handles interaction with the discount database (if there is one).
 */
object DiscountRegistry {

    // HashMap<customer id, Map<Item id, percent discount>>
    private var discounts = HashMap<String, MutableMap<String, Int>>()

    /**
     * If a customer with the specified id exists it returns a map containing the discounts of the
     * customer. If the id doesnt exist returns null.
     *
     * @param customerId is the id of the customer.
     * @return a nullable map where the key is the item id and the value is the discount.
     */
    fun getDiscount(customerId: String): Map<String, Int>? {
        return discounts[customerId]
    }

    /**
     * Adds discount entries to the database for a specified user. If a discount for the specified
     * item already exists the old value is overriden. If no user with the given id exists a new
     * entry is created.
     *
     * @param customerId the id of the customer.
     * @param discount is a map where the key is the item id and the value is the discount.
     * @return the object the method was called on.
     */
    fun addDiscount(customerId: String, discount: Map<String, Int>): DiscountRegistry {
        discounts[customerId]?.let { it.putAll(discount)} ?: discounts.put(customerId, discount.toMutableMap())
        return this
    }

    /**
     * Removes all discounts.
     */
    fun clear() {
        discounts.clear()
    }
}
