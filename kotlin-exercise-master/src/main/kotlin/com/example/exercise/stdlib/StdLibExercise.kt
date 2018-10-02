package com.example.exercise.stdlib

import com.example.data.Result
import java.time.LocalDateTime

fun main(args: Array<String>) {
    StdLibExercise().tryAll()
}

fun getNow(): LocalDateTime = run {
    LocalDateTime.now()
}

/**
 * 標準ライブラリ
 *
 */
class StdLibExercise {

    fun tryAll() {
        pairAndTriple()
        scopeFunctions()
        letWithNullCheck()

        chk("value")
        chk(null)

        chk(true)
        chk(false)

        err(true)
        err(false)

        req("value")
        req(null)

        req(1)
        req(-1)
    }

    // Pair
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html
    // Triple
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-triple/index.html

    fun pairAndTriple() {
        val pair = Pair("key", "value")
        println(pair.toList().toString())

        val triple = Triple("A", "B", "C")
        println(triple.toList().toString())
    }

    // also
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/also.html
    // apply
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/apply.html
    // let
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/let.html
    // run
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/run.html
    // with
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/with.html

    private fun somethingFunc(name: String) {
        println("name:$name, this:$this")
    }

    fun scopeFunctions() {
        // public inline fun <T> T.also(block: (T) -> Unit): T
        val r1 = Result("also", "Mitty").also { result ->
            // resultはレシーバ
            result.amount = 888
            result.change(777)
            somethingFunc(result.name)
            println(result)
            // println(this) // thisはインスタンス
        }
        println(r1)

        // public inline fun <T> T.apply(block: T.() -> Unit): T
        val r2 = Result("apply", "Mitty").apply {
            // thisはレシーバ
            this.amount = 999
            this.change(888)
            somethingFunc(this.name)
            println(this)
        }
        println(r2)

        // public inline fun <T, R> T.let(block: (T) -> R): R
        val r3 = Result("let", "Mitty").let { result ->
            // resultはレシーバ
            result.amount = 777
            result.change(666)
            somethingFunc(result.name)
            println(result)
            // println(this) //thisはインスタンス
            result
        }
        println(r3)

        // public inline fun <T, R> T.run(block: T.() -> R): R
        val r4 = Result("run", "Mitty").run {
            // thisはレシーバ
            this.amount = 666
            this.change(555)
            somethingFunc(this.name)
            println(this)
            this
        }
        println(r4)

        // public inline fun <T, R> with(receiver: T, block: T.() -> R): R
        val r5 = Result("with", "Mitty")
        with(r5) {
            // thisはレシーバ
            this.amount = 555
            this.change(444)
            somethingFunc(this.name)
            println(this)
        }
        println(r5)

        val now = getNow()
        println(now.toString())
    }

    fun letWithNullCheck() {
        val strIsNullable: String? = null

        // nullでなければlet関数を実行する
        // 結果を?:で判定しnullの場合は例外をスローするので
        // 戻り値はNot Nullであることが保証される
        try {
            val strIsNotNull: String = strIsNullable?.let {
                it.toUpperCase()
            } ?: error("String must Not-NULL")
        } catch (e: Exception) {
            println(e)
        }
    }

    // checkNotNull
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/check-not-null.html

    fun chk(v: String?) {
        try {
            // IllegalStateException
            checkNotNull(v) { "must be set beforehand" }
        } catch (e: Exception) {
            println(e)
        }
    }

    // check
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/check.html

    fun chk(v: Boolean) {
        try {
            // IllegalStateException
            check(v) { "State must be non-empty" }
        } catch (e: Exception) {
            println(e)
        }
    }

    // error
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/error.html

    fun err(v: Boolean) {
        try {
            if (v) {
                println("success")
            } else {
                // IllegalStateException
                error { "error" }
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    // requireNotNull
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/require-not-null.html

    fun req(v: String?) {
        try {
            // IllegalArgumentException
            requireNotNull(v) { "" }
        } catch (e: Exception) {
            println(e)
        }
    }

    // require
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/require.html

    fun req(v: Int) {
        try {
            // IllegalArgumentException
            require(v < 0) { "Count must be non-negative," }
        } catch (e: Exception) {
            println(e)
        }
    }

    override fun toString(): String {
        return "*** ClassExercise ***"
    }

}