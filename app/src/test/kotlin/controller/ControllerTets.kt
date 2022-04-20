package controller

import integration.*
import kotlin.test.*
import util.Logger

class ControllerTest {

    val logger = Logger("controller.test.log", true)

    @BeforeTest fun setup() {}

    @AfterTest fun tearDown() {}

    @Test
    fun `fails when entering nonexistent item id`() {
        val controller = createController()
        controller.newSale()

        assertFailsWith(NoSuchElementException::class) { controller.enterItem("-1") }
    }

    @Test
    fun `fails when entering zero or fewer items`() {
        val items = ItemRegistry(mapOf(Item("1", "Apple", 12.1) to 20))
        val controller = createController(ir = items)
        controller.newSale()

        assertFailsWith(IllegalArgumentException::class) { controller.enterItem("1", 0) }
        assertFailsWith(IllegalArgumentException::class) { controller.enterItem("1", -1) }
    }

    @Test
    fun `fails when entering nonexistent customer id`() {
        val discount = DiscountRegistry()
        val controller = createController(dr = discount)
        controller.newSale()
        assertFailsWith(NoSuchElementException::class) { controller.applyDiscount("-1") }
    }

    @Test
    fun `paying logs receipt in external systems`() {
        val item = Item("1", "Apple", 12.1)
        val items = ItemRegistry(mapOf(item to 20))
        val controller = createController(ir = items)
        controller.newSale()
        controller.enterItem("1")

        val actual = controller.pay(100.0)
        val expected = Receipt(listOf(Triple(item, 0, 1)), 100.0)
        assertEquals(expected, actual)
    }

    fun createController(
            printer: Printer = Printer(),
            ir: ItemRegistry = ItemRegistry(),
            dr: DiscountRegistry = DiscountRegistry(),
            sl: SalesLog = SalesLog(),
            ac: Accounting = Accounting()
    ): Controller {
        return Controller(printer, ir, dr, sl, ac, logger)
    }
}
