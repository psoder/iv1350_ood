package model

import integration.Item
import kotlin.test.*

class RegisterTest {

    @Test
    fun `paying increases register balance`() {
        val reg = Register()
        reg.newTransaction()
        val item = Item("1", "a", 10.0)
        reg.enterItem(item)
        reg.pay(100.0)
        assertEquals(reg.balance, 10.0)
    }

    @Test
    fun `paying fails if less than price`() {
        val reg = Register()
        reg.newTransaction()
        val item = Item("1", "a", 10.0)
        reg.enterItem(item)
        assertFailsWith(IllegalArgumentException::class) { reg.pay(1.0) }
    }

    @Test
    fun `newTransactions fails if there is current transaction`() {
        val reg = Register()
        reg.newTransaction()
        assertFailsWith(IllegalStateException::class) { reg.newTransaction() }
    }

    @Test fun `ending transaction set transaction to null`() {
        val reg = Register()
        reg.newTransaction()
        reg.pay(0.0)
        assertNull(reg.transaction)
    }

    @Test fun `enter item updates Transaction`() {
        val reg = Register()
        reg.newTransaction()
        val item = Item("1", "a", 10.0)
        reg.enterItem(item)
        val expected = listOf(Triple(item, 0, 1))
        val actual = reg.transaction?.items?.toList()
        assertEquals(expected, actual)
    }

    @Test fun `pay fails if no transaction`() {
        val reg = Register()
        assertFailsWith(IllegalStateException::class) { reg.pay(10.0) }
    }

    @Test fun `enterItem fails if no transaction`() {
        val reg = Register()
        val item = Item("1", "a", 10.0)
        assertFailsWith(IllegalStateException::class) { reg.enterItem(item) }
    }

    @Test fun `applyDiscount fails if no transaction`() {
        val reg = Register()
        val discounts = mapOf("1" to 25)
        assertFailsWith(IllegalStateException::class) { reg.applyDiscount(discounts) }
    }
}
