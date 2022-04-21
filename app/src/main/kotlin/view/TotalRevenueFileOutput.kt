package view

import java.io.File
import model.RegisterObserver

class TotalRevenueFileOutput : RegisterObserver {

    override fun balanceHasChanged(newBalance: Double) {
        File("balance").appendText("$newBalance\n")
    }

    override fun doShowTotalIncome() {
        File("balance").appendText("doShowTotalIncome\n")
    }

    override fun handleErrors(e: Exception) {
        throw e
    }
}
