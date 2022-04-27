package integration

import model.Item
import model.Receipt
import model.SaleItem
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SalesLogTest {

    val salesLog = SalesLog()

    init {
        salesLog.log(Receipt(listOf(SaleItem(Item("0", "a", 1.2), 4, 5)), 6.7))
    }

    @Test
    fun `logging items adds to log`() {
        val rec = Receipt(listOf(SaleItem(Item("1", "b", 9.8), 7, 6)), 5.4)
        salesLog.log(rec)

        Assertions.assertTrue(salesLog.getLogs().contains(rec))
    }
}
