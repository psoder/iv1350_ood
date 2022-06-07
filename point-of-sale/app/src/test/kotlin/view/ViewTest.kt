package view

import java.io.*
import java.time.*
import java.util.Scanner
import model.*
import controller.*
import integration.*
import util.*
import org.junit.jupiter.api.*

class ViewTest {

    lateinit var view: View
    val eol = System.getProperty("line.separator")
    lateinit var scanner: Scanner

    @BeforeEach
    fun setup() {
        view = View(Controller(Printer(eol), logger = Logger("/" , eol, true)), eol)
    }

    @Test
    @Disabled
    fun `view prints correclty`() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        
        // val input = "ByteArrayInputStream".byteInputStream()
        // System.setIn(input)
        
        scanner = Scanner("1")

        view.handleSale(scanner)
        
        val expected = """
            |Register Balance: 0.00
            |
            |1. New Sale
            |2. Exit
            |
            """.trimMargin()


        Assertions.assertEquals(expected, outContent.toString())
    }
}
