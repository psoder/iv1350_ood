package integration

import java.time.LocalDateTime

/**
 * Responsible for storing information about a transaction. Immutable.
 * 
 * @property items is a Map where the key is the item id and the value is 
 * Triple<Item, Discount as a percentage e.g. 25%, Quantity>.
 * @property amountPaid is how much the customer paid.
 */
data class Receipt(
    val items: Map<String, Triple<Item, Int, Int>>,
    val amountPaid: Double
) {
    val time = LocalDateTime.now()
    val price = items.asIterable().fold(0.0) { acc, (_, v) ->
        acc + (v.first.price).times((100.0 - v.second) / 100.0).times(v.third)
    }
    val vat = items.asIterable().fold(0.0) { acc, (_, v) ->
        acc + (v.first.price * v.first.vat.rate / 100)
            .times((100.0 - v.second) / 100.0)
            .times(v.third)
    }
}
