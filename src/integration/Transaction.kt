package integration

import java.time.LocalDateTime

class Transaction(val place: String, val seller: String, val customerId: String? = "") {
    val items = ArrayList<Item>()
    var discount = 0.0

    fun addItem(item: Item) {
        items.add(item)
    }

    fun getReceipt(): String {
        return """
        |##############
        |Items:
        |${transactionList()}
        |
        |Date: ${LocalDateTime.now()}
        |Place: $place
        |Seller: $seller
        |Customer: $customerId
        |##############
        """.trimMargin()
    }

    fun applyDiscount(amount: Double) {
        discount = amount
    }

    fun price(): Double {
        return items.fold(0.0) { acc, item -> acc + item.price }
    }

    fun transactionList(): String {
        return items
                .groupBy { it.id }
                .asIterable()
                .fold("") { acc, (_, items) ->
                    acc + "${items[0].name} \t ${items.size} x ${items[0].price}\n"
                }
                .plus("Total: ${price()} ()")
    }
}
