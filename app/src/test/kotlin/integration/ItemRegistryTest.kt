package integration

import kotlin.test.*

class ItemRegistryTest {

    @Test
    fun `returns null for nonexistent id`() {
        val ir = ItemRegistry()
        assertNull(ir.getItem("-1"))
    }

    @Test
    fun `updateInventory updates inventory`() {
        val ir = ItemRegistry()
        val item = Item("1", "a", 10.0)
        ir.addItem(item, 10)

        assertNotNull(ir.getItem("1"))
    }
}
