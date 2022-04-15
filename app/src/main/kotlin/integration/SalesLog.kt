package integration

class SalesLog {
    val sales = ArrayList<Receipt>()

    fun log(receipt: Receipt) {
        sales.add(receipt)
    }
}
