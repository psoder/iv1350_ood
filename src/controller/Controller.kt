package controller

import model.Register
import integration.Transaction

class Controller(
    val register: Register,
) {

    fun pay(amount:Double): Unit {
       register.pay(amount)
    }

    fun enterItem(itemId: String): Unit {
        register.enterItem(itemId);
    }

    fun applyDiscount(customerId: String): Unit {
        register.applyDiscount(customerId)
    }

    fun currentTransaction(): Transaction {
        return register.transaction
    }

}