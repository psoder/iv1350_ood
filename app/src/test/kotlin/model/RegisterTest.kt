package model

import org.junit.jupiter.api.*

class RegisterTest {

    @Test
    fun `paying increases register balance`() {
        val reg = Register()
        reg.newSale()
        val item = Item("1", "a", 10.0)
        reg.enterItem(item)
        reg.pay(100.0)
        Assertions.assertEquals("%.2f".format(reg.balance).toDouble(), 11.2)
    }

    @Test
    fun `paying fails if less than price`() {
        val reg = Register()
        reg.newSale()
        val item = Item("1", "a", 10.0)
        reg.enterItem(item)
        Assertions.assertThrows(IllegalArgumentException::class.java) { reg.pay(1.0) }
    }

    @Test
    fun `newSales fails if there is current sale`() {
        val reg = Register()
        reg.newSale()
        Assertions.assertThrows(IllegalStateException::class.java) { reg.newSale() }
    }

    @Test
    fun `ending sale set sale to null`() {
        val reg = Register()
        reg.newSale()
        reg.pay(0.0)
        Assertions.assertNull(reg.sale)
    }

    @Test
    fun `enter item updates Sale`() {
        val reg = Register()
        reg.newSale()
        val item = Item("1", "a", 10.0)
        reg.enterItem(item)
        val expected = listOf(SaleItem(item, 0, 1))
        val actual = reg.sale?.items?.toList()
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `pay fails if no sale`() {
        val reg = Register()
        Assertions.assertThrows(IllegalStateException::class.java) { reg.pay(10.0) }
    }

    @Test
    fun `enterItem fails if no sale`() {
        val reg = Register()
        val item = Item("1", "a", 10.0)
        Assertions.assertThrows(IllegalStateException::class.java) { reg.enterItem(item) }
    }

    @Test
    fun `applyDiscount fails if no sale`() {
        val reg = Register()
        val discounts = mapOf("1" to 25)
        Assertions.assertThrows(IllegalStateException::class.java) { reg.applyDiscount(discounts) }
    }
}
