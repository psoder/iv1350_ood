package model

/**
 * An observer for observing the [Register].
 */
interface RegisterObserver {

    /**
     * Sends information to the observers that a new sale has been made.
     * Should be called when a new sale has been made.
     * 
     * @param newBalance is the new balance of the register.
     */
    fun newSaleWasMade(newBalance: Double) {
        showTotalIncome(newBalance)
    }

    private fun showTotalIncome(newBalance: Double) {
        try {
            showRegisterBalance(newBalance)
        } catch (e: Exception) {
            handleErrors(e)
        }
    }

    /**
     * Notifies the observers about the new register balance.
     * 
     * @param newBalance is the new register balance.
     * @throws Exception when something goes wrong. 
     */
    fun showRegisterBalance(newBalance: Double)

    /**
     * Handles exceptions thrown by [showRegisterBalance]. 
     * 
     * @param e is an exception thrown from [showRegisterBalance].
     */
    fun handleErrors(e: Exception)
}
