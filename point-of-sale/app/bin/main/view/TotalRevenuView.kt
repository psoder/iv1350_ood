package view

import model.RegisterObserver

/**
 * Implementaion of the [RegisterObserver] interface.
 * Writes the new balance to stdout.
 */
class TotalRevenueView : RegisterObserver {

    /**
     * Prints the new balance to stdout.
     *
     * @param newBalance is the new register balance.
     * @throws Exception when something goes wrong.
     */
    override fun showRegisterBalance(newBalance: Double) {
        println("########################")
        println("TRV: Balance: ${"%.2f".format(newBalance)}")
        println("########################")
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
