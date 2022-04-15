package integration

data class Item(
        val id: String,
        val name: String,
        val price: Double,
        val vat: Double = 6.0,
) {}