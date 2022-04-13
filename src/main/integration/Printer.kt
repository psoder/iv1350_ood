package integration

class Printer {

    fun print(receipt: Receipt) {
        val items =receipt.items.asIterable().fold("") { acc, items ->
                    val item = items.value.first
                    acc.plus("\n")
                       .plus("${item.name}\t")
                       .plus("${item.price}\t")
                       .plus("${items.value.third}\t")
                       .plus("${item.vat}%\t")
                       .plus("${items.value.second}%")
                }
        val r = """
        |~~~~~~~~~~~~~~~~~~~~
        |Time:   ${receipt.time}
        |Items:  ${items}
        |VAT:    ${receipt.vat}
        |Price:  ${receipt.price}
        |Paid:   ${receipt.amountPaid}
        |Change: ${receipt.amountPaid - receipt.price}
        |~~~~~~~~~~~~~~~~~~~~
        """.trimMargin()
        println("Printing receipt...")
        println(r)
    }
}
