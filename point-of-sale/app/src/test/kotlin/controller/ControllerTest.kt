package controller

import integration.*
import model.Item
import model.Receipt
import model.SaleItem
import model.VatRate
import org.junit.jupiter.api.*
import util.Logger

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ControllerTest {

    val eol: String = System.getProperty("line.separator")

    val printer = Printer(eol)
    val itemRegistry = ItemRegistry()
    val discountRegistry = DiscountRegistry()
    val salesLog = SalesLog()
    val accounting = Accounting()
    val logger = Logger("controller.test.log", eol, true)

    lateinit var controller: Controller

    init {
        discountRegistry.addDiscount("0", mapOf("1" to 20)).addDiscount("1", mapOf("0" to 10))

        itemRegistry
                .addItem(Item("0", "Apple", 10.0, VatRate.MIDDLE), 10)
                .addItem(Item("1", "Banana", 5.0, VatRate.MIDDLE), 10)
    }

    @BeforeEach
    fun setup() {
        controller =
                Controller(printer, itemRegistry, discountRegistry, salesLog, accounting, logger)
    }

    @Test
    fun `fails when entering nonexistent item id`() {
        controller.newSale()
        Assertions.assertThrows(NoSuchElementException::class.java) { controller.enterItem("-1") }
    }

    @Test
    fun `fails when entering zero or fewer items`() {
        controller.newSale()
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            controller.enterItem("0", 0)
        }
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            controller.enterItem("0", -1)
        }
    }

    @Test
    fun `fails when entering nonexistent customer id`() {
        controller.newSale()
        Assertions.assertThrows(NoSuchElementException::class.java) {
            controller.applyDiscount("-1")
        }
    }

    @Test
    fun `paying returns correct receipt`() {
        controller.newSale()
        controller.enterItem("0", 1)
        val actual = controller.pay(100.0)
        val expected = Receipt(listOf(SaleItem(Item("0", "Apple", 10.0), 0, 1)), 100.0)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `fails when no sale started`() {
        val e = IllegalStateException::class.java
        Assertions.assertThrows(e, { controller.enterItem("0") })
        Assertions.assertThrows(e, { controller.applyDiscount("0") })
        Assertions.assertThrows(e, { controller.pay(10.0) })
    }

    @Test
    fun `fails when starting new sale when sale is in progress`() {
        controller.newSale()
        Assertions.assertThrows(IllegalStateException::class.java, { controller.newSale() })
    }
}
