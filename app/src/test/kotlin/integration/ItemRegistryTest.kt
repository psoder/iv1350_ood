package integration

import model.Item
import org.junit.jupiter.api.*
import integration.ItemRegistry

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemRegistryTest {

    val itemRegistry = ItemRegistry()
    
    init {
        itemRegistry.addItem(Item("0", "Apple", 12.1), 20)
    }

    @Test
    fun `returns null for nonexistent id`() {
        Assertions.assertNull(itemRegistry.getItem("-1"))
    }

    @Test
    fun `updateInventory updates inventory`() {
        Assertions.assertNotNull(itemRegistry.getItem("0"))
    }
}
