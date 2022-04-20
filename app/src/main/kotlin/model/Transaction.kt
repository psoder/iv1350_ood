package model

import integration.Item
import integration.Receipt

/**
 * [Transaction] is a class that has information about a sale. The difference
 * between it and [Receipt] is that [Transaction] is mutable. 
 */
class Transaction {

    //  HashMap<Item id, <Item, discount, quantity>>
    val items = ArrayList<Triple<Item, Int, Int>>()

    /**
     * Adds an item to the cart a given number of times.
     *
     * @param item the item to add.
     * @param discount discount on the item in percent (e.g. 25% is 25).
     * @throws IllegalArgumentException when quantity is less than one.
     */
    fun addItem(item: Item, quantity: Int = 1) {
        require(quantity > 0) { "Quantity ($quantity) must add a positive non-zero integer" }
        val it = items.find { it.first.equals(item) } ?: Triple(item, 0, 0)
        items.remove(it)
        items.add(it.copy(third = it.third + quantity ))
    }

    /**
     * Creates a receipt of the transaction.
     * 
     * @param amountPaid is the amount that was paid
     * @return a Receipt with transaction info 
     */
    fun getReceipt(amountPaid: Double): Receipt {
        return Receipt(items.toList(), amountPaid)
    }

    /**
     * Applies discounts to all items in the transaction.
     * 
     * @param discounts is a Map of item ids and the discount to be apply,
     */
    fun applyDiscount(discounts: Map<String, Int>) {
        for ((id, discount) in discounts) {
            val item = items.find { it.first.id.equals(id) } ?: continue
            val copy = item.copy(second = discount)
            items.remove(item)
            items.add(copy)
        }
    }

    /**
     * Returns the price of the items in the cart, excluding VAT but including
     * discounts.
     *
     * @return the price of the items.
     */
    fun price(): Double {
        return items.fold(0.0) { acc, (item, disc, qty) ->
            acc + (item.price)
                .times((100.0 - disc) / 100.0)
                .times(qty)
        }
    }

    /**
     * The amount of VAT on the current items, including dicounts.
     * 
     * @return the amount of VAT.
     */
    fun vat(): Double {
        return items.asIterable().fold(0.0) { acc, (item, disc, qty) ->
            acc + (item.price * item.vat.rate / 100)
                .times((100.0 - disc) / 100.0)
                .times(qty)
        }
    }
}
