package integration

/** Responsible for handling interaction with the accounting system. */
object Accounting {

    private val receipts = ArrayList<Receipt>()

    /**
     * Logs a reciept in the database of accounts.
     *
     * @param receipt is the receipt of a sale.
     */
    fun log(receipt: Receipt) {
        receipts.add(receipt)
    }

    /**
     * Returns the logs as a list.
     * 
     * @return A list of all receipts.
     */
    fun getLogs(): List<Receipt> {
        return receipts.toList()
    }

    /**
     * Removes all current logs.
     */
    fun clear() {
        receipts.clear()
    }
}
