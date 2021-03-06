package model

import kotlin.math.max

/**
 * [Sale] is a class that has information about a sale. The difference
 * between it and [Receipt] is that [Sale] is mutable. 
 */
class Sale {

    val items = ArrayList<SaleItem>()
    var priceStrategy: PriceStrategy = PriceWithoutVAT
        set(strategy: PriceStrategy) {field = strategy}

    /**
     * Adds an item to the cart a given number of times.
     *
     * @param item the item to add.
     * @param discount discount on the item in percent (e.g. 25% is 25).
     * @throws IllegalArgumentException when quantity is less than one.
     */
    fun addItem(item: Item, quantity: Int = 1) {
        require(quantity > 0) { "Quantity ($quantity) must add a positive non-zero integer" }
        val it = items.find { it.item.equals(item) } ?: SaleItem(item, 0, 0)
        items.remove(it)
        items.add(it.copy(quantity = it.quantity + quantity ))
    }

    /**
     * Creates a receipt of the sale.
     * 
     * @param amountPaid is the amount that was paid
     * @return a Receipt with sale info 
     */
    fun getReceipt(amountPaid: Double): Receipt {
        return Receipt(items.toList(), amountPaid)
    }

    /**
     * Applies discounts to all items in the sale.
     * 
     * @param discounts is a Map of item ids and the discount to be apply,
     * @throws IllegalArgumentException if the discount is negative.
     */
    fun applyDiscount(discounts: Map<String, Int>) {
        for ((id, discount) in discounts) {
            require(discount >= 0) {"Can't apply negative discount (was $discount)"}
            val item = items.find { it.item.id.equals(id) } ?: continue
            val copy = item.copy(discount = discount)
            items.remove(item)
            items.add(copy)
        }
    }

    /**
     * Returns the price of the items in the cart using the selected strategy.
     *
     * @return the price of the items.
     */
    fun price(): Double {
        return priceStrategy.price(items)
    }

    /**
     * The amount of VAT on the current items, including dicounts.
     * 
     * @return the amount of VAT.
     */
    fun vat(): Double {
        return items.asIterable().fold(0.0) { acc, (item, disc, qty) ->
            acc + (max(item.price, 0.0) * item.vat.rate / 100)
                .times((100.0 - disc) / 100.0)
                .times(qty)
        }
    }
}
