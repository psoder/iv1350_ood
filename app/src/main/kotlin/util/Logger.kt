package util

import java.io.File
import java.time.LocalDateTime

/**
 * Creates a new logger that handles logging.
 *
 * @param fielPath is the path to the file to log into.
 * @param quiet if the logger shouldn't log.
 */
class Logger(val filePath: String, val quiet: Boolean = false) {

    /**
     * Logs the text.
     * 
     * @param text is what to log.` 
     */
    fun log(text: String) {
        if (!quiet) {
            File(filePath).appendText("${LocalDateTime.now()}\n$text\n\n")
        }
    }

    /**
     * Logs an exception.
     */
    fun log(e: Exception) = log("${e.message}\n${e.stackTraceToString()}")
}
