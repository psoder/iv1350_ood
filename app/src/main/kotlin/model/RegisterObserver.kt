package model

interface RegisterObserver {
    fun balanceHasChanged(new: Double)
}