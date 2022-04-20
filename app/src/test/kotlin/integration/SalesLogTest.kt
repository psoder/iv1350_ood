package integration

import kotlin.test.*

class SalesLogTest {

    @Test
    fun `logging items adds to log`() {
        val sl = SalesLog()
        val rec = Receipt(listOf(Triple(Item("1", "a", 1.23), 0, 2)), 20.0)
        sl.log(rec)

        assertContains(sl.getLogs(), rec)
    }
}
