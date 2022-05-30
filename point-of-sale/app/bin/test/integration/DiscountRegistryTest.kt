package integration

import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DiscountRegistryTest {

    lateinit var discountRegistry: DiscountRegistry

    @BeforeEach
    fun setup() {
        discountRegistry = DiscountRegistry()
    }

    @Test
    fun `returns null for nonexistent customer id`() {
        Assertions.assertNull(discountRegistry.getDiscount("-1"))
    }
}
