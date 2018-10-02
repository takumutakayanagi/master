package com.example.exercise.lambda

import com.example.data.Result
import java.util.*

// レシーバ付き関数リテラルの定義
val intSum: Int.(Int) -> Int = { other ->
    println("with Receiver. ${this} + $other")
    // thisはレシーバオブジェクト
    this + other
}

fun main(args: Array<String>) {
    LambdaExercise().tryAll()
}

/**
 * ラムダ式
 *
 */
class LambdaExercise {

    fun tryAll() {
        lambda1()
        lambda2()
        lambda3()
        lambda4()
        lambda5()
        lambda6()
        lambda7()
    }

    // Function
    private fun sumIsFunction (x: Int, y: Int): Int { return x + y }

    // Function
    // 関数の本体が単一式の場合
    private fun sumIsFunction2 (x: Int, y: Int): Int = x + y

    // Anonymous Functions
    private val subIsAnonymousFunctions = fun(x: Int, y: Int): Int { return x - y }

    // Anonymous Functions
    // 戻り値の型を省略できる
    private val subIsAnonymousFunctions2 = fun(x: Int, y: Int) = x - y

    // Anonymous Functions
    // 戻り値の型を指定できる
    private val subIsAnonymousFunctions3 = fun(x: Int, y: Int): Long {
        return (x - y).toLong()
    }

    // Lambda Expression Syntax
    // The full syntactic form of lambda expressions, i.e. literals of function types, is as follows:
    private val mulIsLambdaExpression = { x: Int, y: Int -> x * y }

    // Lambda Expression Syntax
    // If we leave all the optional annotations out, what's left looks like this:
    private val divIsLambdaExpression: (x: Int, y: Int) -> Int = { x: Int, y: Int -> x / y }

    // Lambda Expression Syntax
    private val divIsLambdaExpression2: (Int, Int) -> Int = { x, y -> x / y }

    // Lambda Expression Syntax
    private val divIsLambdaExpression3: (Int, Int) -> Long = { x, y -> (x / y).toLong() }


    fun lambda1() {
        println("sum is Function = ${sumIsFunction(2, 4)}" )
        println("sum is Function(2) = ${sumIsFunction2(5, 3)}" )

        println("sub is AnonymousFunction = ${subIsAnonymousFunctions(5, 3)}" )
        println("sub is AnonymousFunction(2) = ${subIsAnonymousFunctions2(5, 3)}" )
        println("sub is AnonymousFunction(3) = ${subIsAnonymousFunctions3(5, 3)}" )

        println("mul is Lambda Expression = ${mulIsLambdaExpression(5, 4)}" )

        println("div is Lambda Expression = ${divIsLambdaExpression(8, 4)}" )
        println("div is Lambda Expression(2) = ${divIsLambdaExpression2(8, 4)}" )
        println("div is Lambda Expression(3) = ${divIsLambdaExpression3(8, 4)}" )
    }

    private fun calc(x: Int, y: Int, op: (Int, Int) -> Int): Long {
        return op(x, y).toLong()
    }

    private fun lambda2() {
        var result: Long

        // Named Arguments
        result = calc(x = 5, y = 2, op = { x: Int, y: Int -> x + y })
        println("result = $result")


        result = calc(5, 2, { x, y -> x + y })
        println("result = $result")

        result = calc(5, 2) { x, y -> x + y }
        println("result = $result")

        result = calc(5, 2, mulIsLambdaExpression)
        println("result = $result")
    }

    private fun calc(x: Int, op: (Int) -> Int): Int {
        return op(x)
    }

    private fun lambda3() {
        var result: Int

        result = calc(x = 10, op = { it: Int -> it / 2 })
        println("result = $result")

        result = calc(10, { it -> it / 2 })
        println("result = $result")

        result = calc(10, { it * 2 })
        println("result = $result")

        result = calc(10) { it * 2 }
        println("result = $result")
    }

    private fun calc2(bound: () -> Int): Int {
        return Random().nextInt(bound())
    }

    private fun lambda4() {
        var result: Int

        result = calc2(bound = { 100 })
        println("result = $result")

        result = calc2() { 100 }
        println("result = $result")

        // ()は省略できる
        result = calc2 { 100 }
        println("result = $result")
    }

    // filter(predicate: (T) -> Boolean)
    // forEach(action: (T) -> Unit)
    private fun lambda5() {
        val list: List<Int> = listOf(1, 1, 2, 3, 3, 4, 5, 6, 6)

        list.filter(predicate = { it: Int -> it % 2 == 0 })
            .forEach(action = { it: Int -> println(it) })

        list.filter({ it -> it % 2 == 0 })
            .forEach({ it -> println(it) })

        // パラメータが1つの場合は省略可能
        list.filter({ it % 2 == 0 })
            .forEach({ println(it) })

        // パラメータの最後がラムダ式がの場合は()の外側に出せる
        list.filter { it % 2 == 0 }
            .forEach { println(it) }
    }

    // レシーバ付きラムダ
    private fun creator(name: String, key: String, actions: Result.() -> Unit): Result {
        val result = Result(name, key)
        // ラムダ式を実行
        result.actions()
        return result
    }

    private val changeAmount = fun Result.(newAmount: Int): Result {
        this.change(newAmount)
        return this
    }

    private fun creator2(name: String, key: String, actions: (Result) -> Unit): Result {
        val result = Result(name, key)
        // ラムダ式を実行
        actions(result)
        return result
    }

    fun lambda6() {

        println("sum = ${5.intSum(5)}")

        // レシーバ付き
        val result = creator("tom", "test") {
            // このブロック内でのthisは、Result型のインスタンス
            amount = 100
            change(200)
        }
        println(result)

        result.changeAmount(300)

        val r2 = creator2("tom", "test") { result ->
            // このブロック内でのthisは、このクラスのインスタンス
            result.amount = 100
            result.change(200)
        }
    }

    private fun numIsEvenNumber(n: Int): Boolean = n % 2 == 0

    fun lambda7() {
        // 関数参照
        (1..10).filter(::numIsEvenNumber).forEach { even ->
            println(even)
        }

        // ラムダ式
        (1..10).filter( { it % 2 == 0 } ).forEach { even ->
            println(even)
        }
        // ラムダ式
        (1..10).filter { it % 2 == 0 }.forEach { even ->
            println(even)
        }

        // 他の変数への代入
        val isEvenNumber = ::numIsEvenNumber

        (1..10).filter(isEvenNumber).forEach { even ->
            println(even)
        }

        // 普通の呼び出し方
        if (isEvenNumber(2)) {
            println("even")
        }

        // 関数参照で呼び出す
        if (::numIsEvenNumber.invoke(2)) {
            println("even")
        }
    }

}