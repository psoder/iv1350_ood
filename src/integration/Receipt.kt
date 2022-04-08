package integration

class Receipt(
    val items: List<Item>,
    val date: String,
    val place: String,
    val seller: String,
) {

    override fun toString(): String {
        var s = "##############\n"
        s += "Items: \n"
        var total = 0.0
        for(item in items) {
            s += "- ${item.name}: ${item.price}\n"
            total += item.price
        }
        s += "Total: $total\n\n"
        s += "Date: $date\n"
        s += "Place: $place\n"
        s += "Seller: $seller\n"
        s += "##############"
        return s
    }
}