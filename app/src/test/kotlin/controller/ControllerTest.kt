package controller

import integration.*
import model.Item
import model.Receipt
import model.SaleItem
import org.junit.jupiter.api.*
import util.Logger

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ControllerTest {

    val printer = Printer()
    val itemRegistry = ItemRegistry()
    val discountRegistry = DiscountRegistry()
    val salesLog = SalesLog()
    val accounting = Accounting()

    val logger = Logger("controller.test.log", true)
    val controller =
            Controller(printer, itemRegistry, discountRegistry, salesLog, accounting, logger)

    init {
        itemRegistry.addItem(Item("0", "Apple", 12.1), 20)
        controller.newSale()
    }

    @BeforeEach fun beforeEach() {}

    @AfterEach fun afterEach() {}

    @Test
    fun `fails when entering nonexistent item id`() {
        Assertions.assertThrows(NoSuchElementException::class.java) { controller.enterItem("-1") }
    }

    @Test
    fun `fails when entering zero or fewer items`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            controller.enterItem("0", 0)
        }
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            controller.enterItem("0", -1)
        }
    }

    @Test
    fun `fails when entering nonexistent customer id`() {
        Assertions.assertThrows(NoSuchElementException::class.java) {
            controller.applyDiscount("-1")
        }
    }

    @Test
    fun `paying logs receipt in external systems`() {
        controller.enterItem("0", 1)
        val actual = controller.pay(100.0)
        val expected = Receipt(listOf(SaleItem(Item("0", "Apple", 12.1), 0, 1)), 100.0)
        Assertions.assertEquals(expected, actual)
    }
}
