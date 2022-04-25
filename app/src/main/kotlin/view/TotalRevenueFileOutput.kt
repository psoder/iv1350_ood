package view

import java.io.File
import model.RegisterObserver

/**
 * Implementaion of the [RegisterObserver] interface.
 * Writes the new balance to a file.
 */
class TotalRevenueFileOutput : RegisterObserver {

    private val eol: String = System.getProperty("line.separator")

    /**
     * Writes the [newBalance] to a file.
     *
     * @param newBalance is the new register balance.
     * @throws Exception when something goes wrong.
     */
    override fun showRegisterBalance(newBalance: Double) {
        File("balance").appendText("$newBalance$eol")
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
