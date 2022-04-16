package integration

/**
 * VAT rate.of a product/service.
 * 
 * @property rate how many percent the rate is.
 */
enum class VatRate(val rate: Double) {
    NONE(0.0),
    LOW(6.0),
    MIDDLE(12.0),
    HIGH(25.0),
}

/**
 * Data class that represents an item.
 *
 * @property id is the id of the product.
 * @property name name of the product.
 * @property price of the product.
 * @property vat is the vat rate.
 */
data class Item(
    val id: String,
    val name: String,
    val price: Double,
    val vat: VatRate = VatRate.LOW,
) {}
