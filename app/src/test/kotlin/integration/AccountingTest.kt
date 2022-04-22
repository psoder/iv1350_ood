package integration

import kotlin.test.*
import model.Item
import model.Receipt
import model.SaleItem

class AccountingTest {

    @Test fun `logging items adds to log`() {
        val acc = Accounting
        val rec = Receipt(listOf(SaleItem(Item("1", "a", 1.23), 0, 2)), 20.0)
        acc.log(rec)

        assertContains(acc.getLogs(), rec)
    }
    
}
