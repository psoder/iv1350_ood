package integration

import java.time.LocalDateTime

data class Receipt(
    val items: Map<String, Pair<Item, Int>>,
    val amountPaid: Double,
    val store: String = "Middle earth",
    val seller: String = "Bilbo Baggins",
) {
    val time = LocalDateTime.now()
}