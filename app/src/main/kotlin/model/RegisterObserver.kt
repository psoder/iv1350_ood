package model

interface RegisterObserver {
    fun balanceHasChanged(newBalance: Double)

    fun newSaleWasMade(newBalance: Double) {
        balanceHasChanged(newBalance)
        showTotalIncome()
    }

    fun showTotalIncome() {
        try {
            doShowTotalIncome()
        } catch (e: Exception) {
            handleErrors(e)
        }
    }

    fun doShowTotalIncome()

    fun handleErrors(e: Exception)
}