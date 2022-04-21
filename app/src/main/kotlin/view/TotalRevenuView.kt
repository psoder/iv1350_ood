package view

import model.RegisterObserver

class TotalRevenueView : RegisterObserver {

    override fun balanceHasChanged(newBalance: Double) {
        println("Balance: $newBalance")
    }

    override fun doShowTotalIncome() {
        println("doShowTotalIncome")
    }

    override fun handleErrors(e: Exception) {
        throw e
    }
}
