package integration

import kotlin.test.*

class ItemRegistryTest {

    @BeforeTest
    fun setup() {
        ItemRegistry.addItem(Item("0", "Apple", 12.1), 20)
    }

    @AfterTest
    fun tearDown() {
        ItemRegistry.clear()
    }

    @Test
    fun `returns null for nonexistent id`() {
        assertNull(ItemRegistry.getItem("-1"))
    }

    @Test
    fun `updateInventory updates inventory`() {
        ItemRegistry.addItem(Item("1", "b", 20.0), 20)
        assertNotNull(ItemRegistry.getItem("1"))
    }
}
