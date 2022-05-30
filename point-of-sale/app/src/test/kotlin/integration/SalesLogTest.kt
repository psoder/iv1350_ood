package integration

import model.Item
import model.Receipt
import model.SaleItem
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SalesLogTest {

    lateinit var salesLog: SalesLog

    @BeforeEach
    fun setup() {
        salesLog = SalesLog()
    }

    @Test
    fun `logging items adds to log`() {
        val rec = Receipt(listOf(SaleItem(Item("1", "b", 9.8), 7, 6)), 5.4)
        salesLog.log(rec)

        Assertions.assertTrue(salesLog.getLogs().contains(rec))
    }
}
