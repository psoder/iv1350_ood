package integration

import kotlin.test.*

class AccountingTest {

    @Test fun `logging items adds to log`() {
        val acc = Accounting
        val rec = Receipt(listOf(Triple(Item("1", "a", 1.23), 0, 2)), 20.0)
        acc.log(rec)

        assertContains(acc.getLogs(), rec)
    }
    
}
