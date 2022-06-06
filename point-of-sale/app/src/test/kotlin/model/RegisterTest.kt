package model

import org.junit.jupiter.api.*

class RegisterTest {

    val item0 = Item("0", "apple", 10.0, VatRate.MIDDLE)
    val discounts = mapOf("1" to 25)
    lateinit var register: Register

    @BeforeEach
    fun setup() {
        register = Register()
    }

    @Test
    fun `paying increases register balance`() {
        register.newSale()
        register.enterItem(item0)
        register.pay(100.0)
        Assertions.assertEquals("%.2f".format(register.balance).toDouble(), 11.2)
    }

    @Test
    fun `paying fails if less than price`() {
        register.newSale()
        register.enterItem(item0)
        Assertions.assertThrows(IllegalArgumentException::class.java) { register.pay(1.0) }
    }

    @Test
    fun `newSales fails if there is current sale`() {
        register.newSale()
        Assertions.assertThrows(IllegalStateException::class.java) { register.newSale() }
    }

    @Test
    fun `ending sale set sale to null`() {
        register.newSale()
        register.pay(0.0)
        Assertions.assertNull(register.currentSale())
    }

    @Test
    fun `enter item updates Sale`() {
        register.newSale()
        register.enterItem(item0)
        val expected = listOf(SaleItem(item0, 0, 1))
        val actual = register.currentSale()?.items?.toList()
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `pay fails if no sale`() {
        Assertions.assertThrows(IllegalStateException::class.java) { register.pay(10.0) }
    }

    @Test
    fun `enterItem fails if no sale`() {
        Assertions.assertThrows(IllegalStateException::class.java) { register.enterItem(item0) }
    }

    @Test
    fun `applyDiscount fails if no sale`() {
        Assertions.assertThrows(IllegalStateException::class.java) {
            register.applyDiscount(discounts)
        }
    }
}
