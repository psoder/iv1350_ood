package view

import model.RegisterObserver

class TotalRevenueView : RegisterObserver {

    override fun balanceHasChanged(new: Double) {
        println("Balance: $new")
    }
}
