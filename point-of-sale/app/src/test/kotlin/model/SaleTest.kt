package model

import org.junit.jupiter.api.*

class SaleTest {

    lateinit var sale: Sale
    val item = Item("0", "apple", 10.0, VatRate.MIDDLE)
    val discounts = mapOf("0" to 25, "1" to 10, "3" to 20)

    @BeforeEach
    fun setup() {
        sale = Sale()
    }

    @Test
    fun `add item updates list`() {
        sale.addItem(item)
        Assertions.assertEquals(sale.items.toList(), listOf(SaleItem(item, 0, 1)))
    }

    @Test
    fun `fails when adding zero or negative quantity`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) { sale.addItem(item, 0) }
        Assertions.assertThrows(IllegalArgumentException::class.java) { sale.addItem(item, -1) }
    }

    @Test
    fun `apply discount decreases price`() {
        sale.addItem(item)
        sale.priceStrategy = PriceWithoutVAT
        val before = sale.price()
        sale.applyDiscount(discounts)
        val after = sale.price()
        Assertions.assertEquals(after, before * 0.75)
    }

    @Test
    fun `fails if applying negative discount`() {
        sale.addItem(item)
        Assertions.assertThrows(IllegalArgumentException::class.java) { sale.applyDiscount(mapOf("0" to -1)) }
    }

    @Test
    fun `correct price`() {
        sale.addItem(item)
        Assertions.assertEquals(10.0, sale.price())
    }

    @Test
    fun `correct vat`() {
        sale.addItem(item)
        Assertions.assertEquals(1.2, sale.vat())
    }
}
