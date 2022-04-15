package integration

import integration.VatRate

enum class VatRate(val rate: Double) {
        NONE(0.0),
        LOW(6.0),
        MIDDLE(12.0),
        HIGH(25.0),
}

data class Item(
        val id: String,
        val name: String,
        val price: Double,
        val vat: VatRate = VatRate.LOW,
) { }