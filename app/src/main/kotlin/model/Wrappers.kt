package model

import java.time.LocalDateTime

/**
 * VAT rate.of a product/service.
 *
 * @param rate how many percent the rate is.
 */
enum class VatRate(val rate: Double) {
    NONE(0.0),
    LOW(6.0),
    MIDDLE(12.0),
    HIGH(25.0),
}

/**
 * Class that represents an item.
 *
 * @param id is the id of the product.
 * @param name name of the product.
 * @param price of the product.
 * @param vat is the vat rate.
 */
data class Item(
    val id: String,
    val name: String,
    val price: Double,
    val vat: VatRate = VatRate.LOW,
) {}

/**
 * Immutable DTO used for associating an item, discount on the item, and quantity.
 * 
 * @param item is the item that's being sold.
 * @param discount is the discount in percent on the item. E.g. 25 for 25%
 * @param quantity is the number of items.
 */
data class SaleItem(val item: Item, val discount: Int, val quantity: Int){}

/**
 * DTO responsible for storing information about a sale. Immutable.
 *
 * @param items is a list of [SaleItem].
 * @param amountPaid is how much the customer paid.
 */
data class Receipt(val items: List<SaleItem>, val amountPaid: Double) {
    
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
