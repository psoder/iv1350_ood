package view

import model.RegisterObserver

class TotalRevenueView : RegisterObserver {

    override fun balanceHasChanged(newBalance: Double) {
        println("########################")
        println("TRV: Balance: ${"%.2f".format(newBalance)}")
        println("########################")
    }

    override fun doShowTotalIncome() {
        println("doShowTotalIncome")
    }

    override fun handleErrors(e: Exception) {
        throw e
    }
}
