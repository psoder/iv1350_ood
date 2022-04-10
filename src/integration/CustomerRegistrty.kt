package integration

import kotlin.random.*

class CustomerRegistry {

    var customers = HashMap<String, Customer>()

    // Create a list of customers
    init {
        val names = listOf("Star Lord", "Batman", "Aragorn", "Darth Vader")
        for (index in names.indices) {
            val discount = Random.nextInt(0, 30)
            customers.put("$index", Customer("$index", names[index], discount))
        }
    }

    fun getDiscount(customerId: String): Int {
        return customers.get(customerId)?.discount
                ?: throw NoSuchElementException("No customer with id $customerId exists")
    }
}

data class Customer(
        val id: String,
        val name: String,
        val discount: Int,
) {}
