package util

import java.io.File
import java.time.LocalDateTime

/**
 * Creates a new logger that handles logging.
 *
 * @param fielPath is the path to the file to log into.
 * @param quiet if the logger shouldn't log.
 * @param eol is the end of line characthers.
 */
class Logger(val filePath: String, val eol: String, var quiet: Boolean = false) {

    init {
        File(filePath).createNewFile()
    }

    /**
     * Logs the text and time. Default time is when the method was invoked.
     *
     * @param text is what to log.
     * @param time is the associated timestamp
     */
    fun log(text: String, time: String = LocalDateTime.now().toString()) {
        if (!quiet) {
            File(filePath).appendText("${time}$eol$text$eol$eol")
        }
    }

    /**
     * Logs an exception and time.
     *
     * @param e is the exception.
     * @param time is the time.
     */
    fun log(e: Exception, time: String = LocalDateTime.now().toString()) {
        log("$eol${e.stackTraceToString()}", time)
    }
}
