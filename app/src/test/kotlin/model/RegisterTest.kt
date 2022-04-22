package model

import kotlin.test.*

class RegisterTest {

    @Test
    fun `paying increases register balance`() {
        val reg = Register()
        reg.newSale()
        val item = Item("1", "a", 10.0)
        reg.enterItem(item)
        reg.pay(100.0)
        assertEquals(reg.balance, 10.6)
    }

    @Test
    fun `paying fails if less than price`() {
        val reg = Register()
        reg.newSale()
        val item = Item("1", "a", 10.0)
        reg.enterItem(item)
        assertFailsWith(IllegalArgumentException::class) { reg.pay(1.0) }
    }

    @Test
    fun `newSales fails if there is current sale`() {
        val reg = Register()
        reg.newSale()
        assertFailsWith(IllegalStateException::class) { reg.newSale() }
    }

    @Test fun `ending sale set sale to null`() {
        val reg = Register()
        reg.newSale()
        reg.pay(0.0)
        assertNull(reg.sale)
    }

    @Test fun `enter item updates Sale`() {
        val reg = Register()
        reg.newSale()
        val item = Item("1", "a", 10.0)
        reg.enterItem(item)
        val expected = listOf(Triple(item, 0, 1))
        val actual = reg.sale?.items?.toList()
        assertEquals(expected, actual)
    }

    @Test fun `pay fails if no sale`() {
        val reg = Register()
        assertFailsWith(IllegalStateException::class) { reg.pay(10.0) }
    }

    @Test fun `enterItem fails if no sale`() {
        val reg = Register()
        val item = Item("1", "a", 10.0)
        assertFailsWith(IllegalStateException::class) { reg.enterItem(item) }
    }

    @Test fun `applyDiscount fails if no sale`() {
        val reg = Register()
        val discounts = mapOf("1" to 25)
        assertFailsWith(IllegalStateException::class) { reg.applyDiscount(discounts) }
    }
}
