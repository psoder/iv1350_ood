package model

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
