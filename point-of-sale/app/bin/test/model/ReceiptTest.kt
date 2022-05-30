package model

import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReceiptTest {

    val items =
            listOf(
                    SaleItem(Item("0", "apple", 10.0, VatRate.MIDDLE), 0, 20),
                    SaleItem(Item("1", "banana", 5.0, VatRate.MIDDLE), 10, 10)
            )

    val receipt = Receipt(items, 100.0)

    @Test
    fun `calculates price correctly`() {
        Assertions.assertEquals((10 * 20) + (5 * 0.9 * 10), receipt.price)
    }

    @Test
    fun `calculates VAT correctly`() {
        Assertions.assertEquals((10 * 20 * 0.12) + (5 * 0.9 * 10 * 0.12), receipt.vat)
    }
}
