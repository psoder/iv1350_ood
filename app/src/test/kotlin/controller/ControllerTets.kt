package controller

import integration.*
import kotlin.test.*
import util.Logger

class ControllerTest {

    init {
        DiscountRegistry
    }

    val logger = Logger("controller.test.log", true)

    @BeforeTest
    fun setup() {
        ItemRegistry.addItem(Item("0", "Apple", 12.1), 20)
    }

    @AfterTest
    fun tearDown() {
        ItemRegistry.clear()
    }

    @Test
    fun `fails when entering nonexistent item id`() {
        val controller = createController()
        controller.newSale()

        assertFailsWith(NoSuchElementException::class) { controller.enterItem("-1") }
    }

    @Test
    fun `fails when entering zero or fewer items`() {
        val controller = createController()
        controller.newSale()

        assertFailsWith(IllegalArgumentException::class) { controller.enterItem("0", 0) }
        assertFailsWith(IllegalArgumentException::class) { controller.enterItem("0", -1) }
    }

    @Test
    fun `fails when entering nonexistent customer id`() {
        val controller = createController()
        controller.newSale()

        assertFailsWith(NoSuchElementException::class) { controller.applyDiscount("-1") }
    }

    @Test
    fun `paying logs receipt in external systems`() {
        val controller = createController()
        controller.newSale()
        controller.enterItem("0")

        val actual = controller.pay(100.0)
        val expected = Receipt(listOf(Triple(Item("0", "Apple", 12.1), 0, 1)), 100.0)
        assertEquals(expected, actual)
    }

    fun createController(): Controller {
        return Controller(
                itemRegistry = ItemRegistry,
                discountRegistry = DiscountRegistry,
                logger = logger
        )
    }
}
