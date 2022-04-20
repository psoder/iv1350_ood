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
    val items: List<Triple<Item, Int, Int>>,
    val amountPaid: Double
) {
    val time = LocalDateTime.now()
    val price = items.fold(0.0) { acc, (item, disc, qty) ->
        acc + (item.price).times((100.0 - disc) / 100.0).times(qty)
    }
    val vat = items.fold(0.0) { acc, (item, disc, qty) ->
        acc + (item.price * item.vat.rate / 100)
            .times((100.0 - disc) / 100.0)
            .times(qty)
    }
}
