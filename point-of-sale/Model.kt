package main

import java.time.LocalDateTime
import kotlin.math.max

class Register {

    private val observers = ArrayList<RegisterObserver>()
    val items = ArrayList<SaleItem>()
    var saleInProgress = false
        private set
    var balance = 0.0
        private set


    /**
     * Starts a new sale.
     *
     * @throws IllegalStateException if there's an active sale.
     */
    fun newSale() {
        if (saleInProgress) {
            throw IllegalStateException("Sale already in progress")
        }
        saleInProgress = true
    }

    /**
     * Ends a sale and increments the registers balance.
     *
     * @param amount is the amount the customer pays.
     * @return a receipt of the sale.
     * @throws IllegalStateException if there is no current sale.
     * @throws IllegalArgumentException if the amount paid is less than the price.
     */
    fun pay(amount: Double): Receipt {
        if (!saleInProgress) throw IllegalStateException("No current sale")
        val price = price()
        require(amount >= price) {
            "You're poor, ${"%.2f".format(amount)} is less than ${"%.2f".format(price)}"
        }

        val receipt = Receipt(items.toList(), amount)
        balance += price
        items.clear()
        saleInProgress = false
        notifyObservers()

        return receipt
    }

    /**
     * If there's an onging sale, add the given number of item to the cart.
     *
     * @param item the item to add.
     * @param quantity is the number of items to add.
     * @throws IllegalStateException if there is no ongoing sale.
     * @throws IllegalArgumentException if the quantity is less than one.
     */
    fun enterItem(item: Item, quantity: Int = 1) {
        if (!saleInProgress) throw IllegalStateException("No current sale")
        require(quantity > 0) { "Quantity ($quantity) must add a positive non-zero integer" }

        val it = items.find { it.item.equals(item) } ?: SaleItem(item, 0, 0)
        items.remove(it)
        items.add(it.copy(quantity = it.quantity + quantity))
    }

    /**
     * Applies any applicable discounts to the sale based on the customers id.
     *
     * @param discounts are the discounts to apply Map<Iten id, discount>.
     * @throws IllegalStateException if there is no current sale.
     * @throws IllegalArgumentException if the discount is negative.
     */
    fun applyDiscount(discounts: Map<String, Int>) {
        if (!saleInProgress) throw IllegalStateException("No current sale")

        for ((id, discount) in discounts) {
            require(discount >= 0) { "Can't apply negative discount (was '$discount')" }
            val item = items.find { it.item.id.equals(id) } ?: continue
            val copy = item.copy(discount = discount)
            items.remove(item)
            items.add(copy)
        }
    }

    /**
     * Returns the price of the items in the cart using the provided strategy.
     *
     * @param strategy is the strategy to use. Default is [PriceWithVat]
     * @return the price returned by the provided strategy.
     */
    fun price(strategy: PriceStrategy = PriceWithVAT): Double {
        return strategy.price(items.toList())
    }

    /**
     * Adds an [RegisterObserver] to the observers. The observer is notified when the state changes.
     * 
     * @param obs is an object that implements the [RegisterObserver] interface.
     * @return The object the method was called on. 
     */
    fun addObserver(obs: RegisterObserver): Register {
        observers.add(obs)
        return this
    }

    private fun notifyObservers() = observers.forEach { it.newSaleWasMade(balance) }
}

/**
 * VAT rate.of a product/service.
 *
 * @param rate how many percent the rate is.
 */
enum class VatRate(val rate: Double) {
    NONE(0.0),
    LOW(6.0),
    MIDDLE(12.0),
    HIGH(25.0),
}

/**
 * Class that represents an item.
 *
 * @param id is the id of the product.
 * @param name name of the product.
 * @param price of the product.
 * @param vat is the vat rate.
 */
data class Item(
        val id: String,
        val name: String,
        val price: Double,
        val vat: VatRate = VatRate.MIDDLE,
) {}

/**
 * Immutable DTO used for associating an item, discount on the item, and quantity.
 *
 * @param item is the item that's being sold.
 * @param discount is the discount in percent on the item. E.g. 25 for 25%
 * @param quantity is the number of items.
 */
data class SaleItem(val item: Item, val discount: Int, val quantity: Int) {}

/**
 * DTO responsible for storing information about a sale. Immutable.
 *
 * @param items is a list of [SaleItem].
 * @param amountPaid is how much the customer paid.
 */
data class Receipt(val items: List<SaleItem>, val amountPaid: Double) {
    val time = LocalDateTime.now()
    val price = PriceWithoutVAT.price(items)
    val vat = PriceOfVAT.price(items)
}

/** A strategy for calculating the price of a list of items and their quantities and discounts. */
interface PriceStrategy {

    /**
     * Returns the price of the items in the list.
     *
     * @param items is the list of the items that were sold as well as the quantity and discount.
     * @return the price of the items.
     */
    fun price(items: List<SaleItem>): Double
}

/** Singleton that is a price strategy that calculates the price wihout vat. */
object PriceWithoutVAT : PriceStrategy {

    /**
     * Calculates the price for the given list excluding VAT.
     *
     * @param items is the list of the items that were sold as well as the quantity and discount.
     * @return the price of the items.
     */
    override fun price(items: List<SaleItem>): Double {
        return items.fold(0.0) { acc, (item, disc, qty) ->
            acc + max(item.price, 0.0).times((100.0 - disc) / 100.0).times(qty)
        }
    }
}

/** Singleton that is a price strategy that calculates the price with vat. */
object PriceWithVAT : PriceStrategy {

    /**
     * Calculates the price for the given list inluding VAT.
     *
     * @param items is the list of the items that were sold as well as the quantity and discount.
     * @return the price of the items.
     */
    override fun price(items: List<SaleItem>): Double {
        return items.fold(0.0) { acc, (item, disc, qty) ->
            acc +
                    max(item.price, 0.0)
                            .times(1 + item.vat.rate / 100)
                            .times((100.0 - disc) / 100.0)
                            .times(qty)
        }
    }
}

/** Singleton that is a price strategy that calculates the price of vat. */
object PriceOfVAT : PriceStrategy {

    /**
     * Calculates the price of the vat for the given list of items.
     *
     * @param items is the list of the items that were sold as well as the quantity and discount.
     * @return the price of the items.
     */
    override fun price(items: List<SaleItem>): Double {
        return items.fold(0.0) { acc, (item, disc, qty) ->
            acc + (item.price * item.vat.rate / 100).times((100.0 - disc) / 100.0).times(qty)
        }
    }
}

/** An observer for observing the [Register]. */
interface RegisterObserver {

    /**
     * Sends information to the observers that a new sale has been made. Should be called when a new
     * sale has been made.
     *
     * @param newBalance is the new balance of the register.
     */
    fun newSaleWasMade(newBalance: Double) {
        showTotalIncome(newBalance)
    }

    private fun showTotalIncome(newBalance: Double) {
        try {
            showRegisterBalance(newBalance)
        } catch (e: Exception) {
            handleErrors(e)
        }
    }

    /**
     * Notifies the observers about the new register balance.
     *
     * @param newBalance is the new register balance.
     * @throws Exception when something goes wrong.
     */
    fun showRegisterBalance(newBalance: Double)

    /**
     * Handles exceptions thrown by [showRegisterBalance].
     *
     * @param e is an exception thrown from [showRegisterBalance].
     */
    fun handleErrors(e: Exception)
}
