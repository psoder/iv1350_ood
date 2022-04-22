package model

import integration.Item
import kotlin.math.max

/**
 * A strategy for calculating the price of a list of items and their quantities and discounts.
 */
interface PriceStrategy {
    
    /**
     * Returns the price of the items in the list.
     * 
     * @param items is the list of the items that were sold and the quantity and discount.
     * List<Triple<Item, Discount, Quantity>>.
     * @return the price of the items.
     */
    fun price(items: List<Triple<Item, Int, Int>>): Double
}

/**
 * Singleton that is a price strategy that calculates the price wihout vat.
 */
object PriceWithoutVAT : PriceStrategy {

    /**
     * Calculates the price for the given list excluding VAT.
     *
     * @param items is the list of the items that were sold and the quantity and discount.
     * List<Triple<Item, Discount, Quantity>>.
     * @return the price of the items.
     */
    override fun price(items: List<Triple<Item, Int, Int>>): Double {
        return items.fold(0.0) { acc, (item, disc, qty) ->
            acc + max(item.price, 0.0)
                .times((100.0 - disc) / 100.0)
                .times(qty)
        }
    }
}

/**
 * Singleton that is a price strategy that calculates the price with vat.
*/
object PriceWithVAT : PriceStrategy {
    
    /**
     * Calculates the price for the given list inluding VAT.
     * 
     * @param items is the list of the items that were sold and the quantity
     * and discount. List<Triple<Item, Discount, Quantity>>.
     * @return the price of the items.
     */
    override fun price(items: List<Triple<Item, Int, Int>>): Double {
        return items.fold(0.0) { acc, (item, disc, qty) ->
            acc + max(item.price, 0.0)
                .times(1 + item.vat.rate / 100)
                .times((100.0 - disc) / 100.0)
                .times(qty)
        }
    }
}
