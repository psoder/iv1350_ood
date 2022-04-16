package integration

/**
 * Responsible for handling interaction with the sales log database 
 */
class SalesLog {

    private val sales = ArrayList<Receipt>()

    /**
     * Logs a reciept in the database of sales.
     * 
     * @param receipt is the receipt of a transaction.
     */
    fun log(receipt: Receipt) {
        sales.add(receipt)
    }
    
    /**
     * Returns the logs as a List
     */
    fun getLogs(): List<Receipt> {
        return sales.toList()
    }
}
