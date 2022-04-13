package integration

class Accounting {
    
    val receipts = ArrayList<Receipt>()
    
    fun log(receipt: Receipt) {
        receipts.add(receipt)
    }
}