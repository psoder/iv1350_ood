package util

import java.io.File
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoggerTest {

    val eol: String = System.getProperty("line.separator")
    val filePath = "controller.test.log"
    var logger = Logger(filePath, eol, false)

    @BeforeEach
    fun setup() {
        logger = Logger(filePath, eol, false)
    }

    @AfterEach
    fun tearDown() {
        File(filePath).delete()
    }

    @Test
    fun `logs exceptions correctly`() {
        val e = Exception("Some text")
        logger.log(e, "now")
        val actual = File(filePath).readText()
        val expected = "now$eol$eol${e.stackTraceToString()}$eol$eol"
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `logs text correctly`() {
        logger.log("hello", "now")
        val actual = File(filePath).readText()
        val expected = "now${eol}hello$eol$eol"
        Assertions.assertEquals(expected, actual)
    }

    @Test fun `no output if quiet`() {
        logger.quiet = true
        logger.log("hello", "now")
        val actual = File(filePath).readText()
        val expected = ""
        Assertions.assertEquals(expected, actual)
    }
}
