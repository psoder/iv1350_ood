package model

import kotlin.test.*

class SaleTest {

    @Test
    fun `add item updates list`() {
        val tr = Sale()
        val item = Item("1", "a", 10.0)
        tr.addItem(item)

        assertEquals(tr.items.toList(), listOf(SaleItem(item, 0, 1)))
    }

    @Test
    fun `fails when adding zero or negative quantity`() {
        val tr = Sale()
        val item = Item("1", "a", 10.0)
        
        assertFailsWith(IllegalArgumentException::class) { tr.addItem(item, 0) }
        assertFailsWith(IllegalArgumentException::class) { tr.addItem(item, -1) }
    }

    @Test
    fun `apply discount decreases price`() {
        val tr = Sale()
        val item = Item("1", "a", 10.0)
        val discounts = mapOf("1" to 25)
        tr.addItem(item)

        val before = tr.price()
        tr.applyDiscount(discounts)
        val after = tr.price()

        assertEquals(after, before * 0.75)
    }

    @Test
    fun `fails if applying negative discount`() {
        val tr = Sale()
        val item = Item("1", "a", 10.0)
        val discounts = mapOf("1" to -25)
        tr.addItem(item)

        assertFailsWith(IllegalArgumentException::class) { tr.applyDiscount(discounts) }
    }

    @Test fun `correct price`() {
        val tr = Sale()
        val item = Item("1", "a", 10.0)
        tr.addItem(item)

        assertEquals(tr.price(), 10.0)
    }

    @Test fun `correct vat`() {
        val tr = Sale()
        val item = Item("1", "a", 10.0, VatRate.LOW)
        tr.addItem(item)

        assertEquals(tr.vat(), 0.6)
    }

    @Test fun `correct vat if price is negative`() {
        val tr = Sale()
        val item = Item("1", "a", -10.0, VatRate.LOW)
        tr.addItem(item)

        assertEquals(tr.vat(),  0.0)
    }
}
