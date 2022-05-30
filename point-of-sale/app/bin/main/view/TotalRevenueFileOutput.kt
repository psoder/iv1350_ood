package view

import java.io.File
import model.RegisterObserver

/**
 * Implementaion of the [RegisterObserver] interface.
 * Writes the new balance to a file.
 * 
 * @param filePath is the path to the output file.
 * @param eol is the end of line characthers.
 */
class TotalRevenueFileOutput(val filePath: String, val eol: String) : RegisterObserver {

    /**
     * Writes the [newBalance] to a file.
     *
     * @param newBalance is the new register balance.
     * @throws Exception when something goes wrong.
     */
    override fun showRegisterBalance(newBalance: Double) {
        File(filePath).appendText("$newBalance$eol")
    }
    
    /**
     * Handles exceptions thrown by [showRegisterBalance].
     *
     * @param e is an exception thrown from [showRegisterBalance].
     */
    override fun handleErrors(e: Exception) {
        throw e
    }
}
