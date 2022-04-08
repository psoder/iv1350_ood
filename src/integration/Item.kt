package integration

class Item(
    val id: String,
    val name: String,
    var price: Double,
    val vat: Double = 6.0,
) {
    init {
        price = Math.round(price * 10) / 10.0
    }

    override fun toString(): String {
        return "$id \t $name \t $price \t $vat"
    }
}