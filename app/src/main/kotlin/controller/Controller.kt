package controller

import integration.*
import model.Register
import model.Item
import model.Receipt
import java.io.File
import java.io.PrintWriter
import util.Logger
import util.NoSuchServiceException

/**
 * Respinsible for coordinating interaction between views and the rest of the system. 
 * 
 * @param printer the printer to use.
 * @param itemRegistry the item registry to use.
 * @param discountRegistry the discount registry to use.
 * @param salesLog the place sales should be logged to.
 * @param accounting the accounting system to use.
 * @param logger the logger to use.
 */
class Controller (
    private val printer: Printer = Printer,
    private val itemRegistry: ItemRegistry = ItemRegistry,
    private val discountRegistry: DiscountRegistry = DiscountRegistry,
    private val salesLog: SalesLog = SalesLog,
    private val accounting: Accounting = Accounting,
    private val logger: Logger = Logger("controller.log"),
) {
    val register: Register = Register()

    /**
     * Starts a new sale.
     * 
     * @throws IllegalStateException when there's an ongoing sale.
     */
    fun newSale() {
        try {
            register.newSale()
        } catch (e: IllegalStateException) {
            logger.log(e)
            throw e
        } catch (e: Exception) {
            logger.log(e)
        }
    }

    /**
     * Adds the given number of items to the cart if a item with the id exists.
     *
     * @param id is the item id.
     * @param quantity is how many to add.
     * @throws NoSuchElementException when there's no item with the specified id.
     * @throws IllegalStateException if there is no current sale.
     * @throws IllegalArgumentException if the quantity is less than one.
     * @throws NoSuchServiceException if one of the services isn't running.
     */
    fun enterItem(id: String, quantity: Int = 1) {
        try {
            val item: Item = itemRegistry.getItem(id) 
                ?: throw NoSuchElementException("No item with id '$id' exists")
            register.enterItem(item, quantity)

        } catch (e: NoSuchElementException) {
            logger.log(e)
            throw e
        } catch (e: NoSuchServiceException) {
            logger.log(e)
            throw NoSuchServiceException("One of the services is not working.")
        } catch (e: IllegalArgumentException) {
            logger.log(e)
            throw e
        } catch (e: IllegalStateException) {
            logger.log(e)
            throw IllegalStateException("You must start a sale before entering an item")
        } catch (e: Exception) {
            logger.log(e)
        }
    }

    /**
     * Applies a discount to the customer based on the customers id.
     *
     * @param customerId is the id of the customer.
     * @throws NoSuchElementException when there's no customer with the specified id.
     * @throws IllegalStateException if there is no current sale.
     * @throws IllegalArgumentException if the discount is negative.
     */
    fun applyDiscount(customerId: String) {
        try {
            val discounts: Map<String, Int> = 
            discountRegistry.getDiscount(customerId)
                ?: throw NoSuchElementException("No customer with id '$customerId' exists")
            register.applyDiscount(discounts)
        } catch (e: NoSuchElementException) {
            logger.log(e)
            throw e
        } catch (e: IllegalStateException) {
            logger.log(e)
            throw IllegalStateException("You must start a sale before applying a discount")
        } catch (e: Exception) {
            logger.log(e)
        }
    }

    /**
     * Ends the current sale and pays for the items and prints a receipt
     * for the sale.
     *
     * @param amount is the amount the customer pays
     * @return a nullable receipt of the sale
     * @throws IllegalArgumentException if the amount is less than required.
     * @throws IllegalStateException if there's no ongoing sale.
     */
    fun pay(amount: Double): Receipt? {
        var receipt: Receipt? = null
        try {
            receipt = register.pay(amount)
            printer.print(receipt)
            salesLog.log(receipt)
            accounting.log(receipt)
            return receipt
        } catch (e: IllegalArgumentException) {
            logger.log(e)
            throw e
        } catch (e: IllegalStateException) {
            logger.log(e)
            throw IllegalStateException("You must start a sale before paying")
        } catch (e: Exception) {
            logger.log(e)
        }
        return receipt
    }
}
