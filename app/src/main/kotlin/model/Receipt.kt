package model

import java.time.LocalDateTime

/**
 * DTO responsible for storing information about a sale. Immutable.
 * 
 * @param items is a list of [SaleItem].
 * @param amountPaid is how much the customer paid.
 */
data class Receipt(
    val items: List<SaleItem>,
    val amountPaid: Double
) {
    val time = LocalDateTime.now()

    val price = items.fold(0.0) { acc, (item, disc, qty) ->
        acc + (item.price)
            .times((100.0 - disc) / 100.0)
            .times(qty)
    }

    val vat = items.fold(0.0) { acc, (item, disc, qty) ->
        acc + (item.price * item.vat.rate / 100)
            .times((100.0 - disc) / 100.0)
            .times(qty)
    }
}
