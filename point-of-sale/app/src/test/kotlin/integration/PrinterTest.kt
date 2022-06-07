package integration

import model.Item
import model.Receipt
import model.SaleItem
import model.VatRate
import org.junit.jupiter.api.*
import java.io.*
import java.time.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrinterTest {

    lateinit var printer: Printer
    val eol = System.getProperty("line.separator")

    @BeforeEach
    fun setup() {
        printer = Printer(eol)
    }

    @Test
    fun `printer pritns receipt correctly`() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        val receipt = Receipt(listOf(SaleItem(Item("0", "Apple", 10.0, VatRate.MIDDLE), 10, 1)), 100.0)
        val time = receipt.time

        val expected = """
            |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            |Time:   $time
            |-----------------------------------------
            |Item:   Price:  Qty:    VAT:    Discount:
            |-----------------------------------------
            |Apple   10.0    1       12.0%   10%
            |Total:  ${10*1.12*0.9}
            |-----------------------------------------
            |Price:  ${10*1.12*0.9}
            |VAT:    ${10*0.12*0.9}
            |Paid:   100.0
            |Change: ${100-10*1.12*0.9}
            |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            """.trimMargin()

        printer.print(receipt)

        Assertions.assertEquals(expected, outContent.toString())
    }
}
