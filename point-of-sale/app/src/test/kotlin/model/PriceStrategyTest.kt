package model

import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PriceStrategyTest {

    val items =
            listOf(
                    SaleItem(Item("0", "apple", 10.0, VatRate.MIDDLE), 0, 20),
                    SaleItem(Item("1", "banana", 5.0, VatRate.MIDDLE), 10, 10)
            )

    @Test
    fun `PriceWithVAT returns correct price`() {
        Assertions.assertEquals((10 * 1.12 * 20) + (5 * 1.12 * 0.9 * 10), PriceWithVAT.price(items))
    }

    @Test fun `PriceWithoutVAT returns correct price`() {
        Assertions.assertEquals((10 * 20) + (5 * 0.9 * 10), PriceWithoutVAT.price(items))
    }
}
