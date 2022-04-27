package integration

import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DiscountRegistryTest {

    val discountRegistry = DiscountRegistry()

    init {}

    @Test
    fun `returns null for nonexistent customer id`() {

        Assertions.assertNull(discountRegistry.getDiscount("-1"))
    }
}
