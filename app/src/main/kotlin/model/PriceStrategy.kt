package model

import integration.Item
import kotlin.math.max

interface PriceStrategy {
    fun price(items: List<Triple<Item, Int, Int>>): Double
}

object PriceWithoutVAT : PriceStrategy {

    override fun price(items: List<Triple<Item, Int, Int>>): Double {
        return items.fold(0.0) { acc, (item, disc, qty) ->
            acc + max(item.price, 0.0)
                .times((100.0 - disc) / 100.0)
                .times(qty)
        }
    }
}

object PriceWithVAT : PriceStrategy {
    
    override fun price(items: List<Triple<Item, Int, Int>>): Double {
        return items.fold(0.0) { acc, (item, disc, qty) ->
            acc + max(item.price, 0.0)
                .times(1 + item.vat.rate / 100)
                .times((100.0 - disc) / 100.0)
                .times(qty)
        }
    }
}
