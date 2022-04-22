package integration

import kotlin.test.*
import model.Item
import model.Receipt

class SalesLogTest {

    init {
    }
    
    @BeforeTest
    fun setup() {
        SalesLog.log(Receipt(listOf(Triple(Item("0", "a", 1.2), 4, 5)), 6.7))
    }

    @AfterTest
    fun tearDown() {
        ItemRegistry.clear()
    }

    @Test
    fun `logging items adds to log`() {
        val rec = Receipt(listOf(Triple(Item("1", "b", 9.8), 7, 6)), 5.4)
        SalesLog.log(rec)

        assertContains(SalesLog.getLogs(), rec)
    }
}
