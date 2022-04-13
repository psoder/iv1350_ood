package integration

import java.time.LocalDateTime

data class Receipt(
        val items: Map<String, Triple<Item, Int, Int>>,
        val amountPaid: Double,
        val price: Double,
        val vat: Double,
) {
    val time = LocalDateTime.now()
}
