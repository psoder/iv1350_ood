package integration

/**
 * Abstraction for a printer
 */
class Printer {

    /**
     * Prints a given receipt to stout
     * 
     * @parm the receipt
     */
    fun print(receipt: Receipt) {
        val items = receipt.items.asIterable().fold("") { acc, items ->
                    val item = items.value.first
                    acc.plus("${item.name}\t")
                       .plus("${item.price}\t")
                       .plus("${items.value.third}\t")
                       .plus("${item.vat.rate}%\t")
                       .plus("${items.value.second}%\n")
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
