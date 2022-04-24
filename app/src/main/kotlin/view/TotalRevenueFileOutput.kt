package view

import java.io.File
import model.RegisterObserver

class TotalRevenueFileOutput : RegisterObserver {

    private val eol: String = System.getProperty("line.separator")

    override fun balanceHasChanged(newBalance: Double) {
        File("balance").appendText("$newBalance$eol")
    }

    override fun doShowTotalIncome() {
        File("balance").appendText("doShowTotalIncome$eol")
    }

    override fun handleErrors(e: Exception) {
        throw e
    }
}
