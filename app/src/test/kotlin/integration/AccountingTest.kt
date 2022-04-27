package integration

import model.Item
import model.Receipt
import model.SaleItem
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountingTest {

    val accounting = Accounting() 

    init {}

    @Test
    fun `logging items adds to log`() {
        val rec = Receipt(listOf(SaleItem(Item("1", "a", 1.23), 0, 2)), 20.0)
        accounting.log(rec)

        Assertions.assertTrue(accounting.getLogs().contains(rec))
    }
}
