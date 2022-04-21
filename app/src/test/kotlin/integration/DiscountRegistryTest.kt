package integration

import kotlin.test.*

class DiscountRegistryTest {

    @Test
    fun `returns null for nonexistent customer id`() {
        val dis = DiscountRegistry
        
        assertNull(dis.getDiscount("-1"))
    }
}
