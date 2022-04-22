package integration

/**
 * Abstraction for a printer
 */
object Printer {

    /**
     * Prints a given receipt to standard out.
     * 
     * @param the receipt to print
     */
    fun print(receipt: Receipt) {
        val items = receipt.items.fold("") {
                acc, (item, disc, qty) ->
                acc.plus("${item.name}\t")
                    .plus("${item.price}\t")
                    .plus("${qty}\t")
                    .plus("${item.vat.rate}%\t")
                    .plus("${disc}%\n")
            }

        println("""
            |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            |Time:   ${receipt.time}
            |-----------------------------------------
            |Item:   Price:  Qty:    VAT:    Discount:
            |-----------------------------------------
            |${items}Total:  ${"%.2f".format(receipt.price + receipt.vat)}
            |-----------------------------------------
            |Price:  ${"%.2f".format(receipt.price + receipt.vat)}
            |VAT:    ${"%.2f".format(receipt.vat)}
            |Paid:   ${"%.2f".format(receipt.amountPaid)}
            |Change: ${"%.2f".format(receipt.amountPaid - (receipt.vat + receipt.price))}
            |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            """.trimMargin()
        )
    }
}
