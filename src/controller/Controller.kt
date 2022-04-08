package controller

import model.Register
import view.View

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

}