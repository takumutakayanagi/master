package com.example.data

// primary constructor
open class Result(_key: String, _name: String) {

    companion object {
        private const val ANON: String = "ANONYMOUS"
    }

    val key: String = _key.also { println("set key:$it") }
    val name: String = _name.also { println("set name:$it") }
    var amount: Int = 0
    private var oldAmount: Int = 0

    // secondary constructor
    constructor(_key: String, _amount: Int = 0): this(_key, ANON) {
        amount = _amount
    }

    fun change(newAmount: Int): Unit {
        oldAmount = amount
        amount = newAmount
    }

    override fun toString(): String {
        return "key:$key, name:$name, amount:$amount, oldAmount:$oldAmount"
    }

}
