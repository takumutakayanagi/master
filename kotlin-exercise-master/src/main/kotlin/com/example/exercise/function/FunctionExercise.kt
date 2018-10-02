package com.example.exercise.function

// 拡張関数の定義
fun Int.extSum(other: Int): Int {
    println("extSum: ${this} + $other")
    return this + other
}

fun main(args: Array<String>) {
    FunctionExercise().tryAll()
}

class FunctionExercise {

    fun tryAll() {
        demoExtFunction()
    }

    fun demoExtFunction() {
        val receiverObject = 3
        val result = receiverObject.extSum(5)
        println("receiver:$receiverObject, result:$result")
    }

}