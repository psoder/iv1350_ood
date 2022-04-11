package model

import integration.Item
import integration.Receipt

class Transaction(val place: String, val seller: String) {

    val items = HashMap<String, Pair<Item, Int>>()

    fun addItem(item: Item, discount: Int = 0) {
        items.put(item.id, Pair(item, discount))
    }

    fun getReceipt(amountPaid: Double): Receipt {
        return Receipt(items.toMap(), amountPaid)
    }

    fun price(): Double {
        return items.asIterable().fold(0.0) { acc, items ->
            acc + items.value.first.price * (100 - items.value.second)
        }
    }
}
