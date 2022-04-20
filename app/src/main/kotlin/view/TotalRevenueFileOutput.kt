package view

import model.RegisterObserver
import java.io.File
class TotalRevenueFileOutput : RegisterObserver {

    override fun balanceHasChanged(new: Double) {
        File("balance").appendText("Balance: $new\n")
    }

}