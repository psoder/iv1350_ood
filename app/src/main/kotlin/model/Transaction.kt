package model

import integration.Item
import integration.Receipt

/**
 * [Transaction] is a class that has information about a sale. The difference
 * between it and [Receipt] is that [Transaction] is mutable. 
 */
class Transaction {

    //  HashMap<Item id, <Item, discount, quantity>>
    val items = HashMap<String, Triple<Item, Int, Int>>()

    /**
     * Adds an item to the cart.
     *
     * @param item the item to add.
     * @param discount discount on the item in percent (e.g. 25% is 25).
     */
    fun addItem(item: Item) {
        if (items.containsKey(item.id)) {
            // Since Triple<> is immutable we have to create a copy qith the
            // new value
            val c = items.get(item.id)!!.copy(third = items.get(item.id)!!.third + 1)
            items.remove(item.id)
            items.put(item.id, c)
        } else {
            items.put(item.id, Triple(item, 0, 1))
        }
    }

    /**
     * Creates a receipt of the transaction.
     * 
     * @param amountPaid is the amount that was paid
     * @return a Receipt with transaction info 
     */
    fun getReceipt(amountPaid: Double): Receipt {
        return Receipt(items.toMap(), amountPaid)
    }

    /**
     * Applies discounts to all items in the transaction.
     * 
     * @param discounts is a Map of item ids and the discount to be apply,
     */
    fun applyDiscount(discounts: Map<String, Int>) {
        for ((id, discount) in discounts) {
            // If the item doesnt exist in the list continue
            val c = items.get(id)?.copy(second = discount) ?: continue
            // Apply discount
            items.remove(id)
            items.put(id, c)
        }
    }

    /**
     * Returns the price of the items in the cart, excluding VAT but including
     * discounts.
     *
     * @return the price of the items.
     */
    fun price(): Double {
        return items.asIterable().fold(0.0) { acc, (_, v) ->
            acc + (v.first.price)
                .times((100.0 - v.second) / 100.0)
                .times(v.third)
        }
    }

    /**
     * The amount of VAT on the current items, including dicounts.
     * 
     * @return the amount of VAT.
     */
    fun vat(): Double {
        return items.asIterable().fold(0.0) { acc, (_, v) ->
            acc + (v.first.price * v.first.vat.rate / 100)
                .times((100.0 - v.second) / 100.0)
                .times(v.third)
        }
    }
}
