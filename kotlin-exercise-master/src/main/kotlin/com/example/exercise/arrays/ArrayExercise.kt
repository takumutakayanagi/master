package com.example.exercise.arrays

import java.util.*

fun main(args: Array<String>) {
    ArrayExercise().tryAll()
}

/**
 * 配列
 *
 */
class ArrayExercise {

    fun tryAll() {
        demoFactoryMethod()
        demoConstructorWithLambda()
        demoIndeies()

        demoConvert()
        demoToString()
    }

    fun demoFactoryMethod() {
        val array = arrayOf("A", "B", "C", "D", "E")
        array.joinToString().also { println(it) }

        // intの配列を生成する
        val intArray = intArrayOf(1, 2, 3, 4, 5)
        intArray.joinToString(": ").also { println(it) }

        // 空の配列を生成する
        val emptyArray = emptyArray<String>()
        println(emptyArray.size)
    }

    fun demoConstructorWithLambda() {
        val array = Array(size = 5, init = { i ->
            if (i % 2 == 0) {
                "*"
            } else {
                Random().nextInt(i * 10).toString()
            }
        })
        array.joinToString(",", "<", ">").also { println(it) }
    }

    fun demoIndeies() {
        val array = arrayOf("A", "B", "C", "B", "D", "A")

        for (i in array.indices) {
            println(array[i])
        }

        for ((index, value) in array.withIndex()) {
            println("index=$index, value=$value")
        }

        for ((index, value) in array.distinct().withIndex()) {
            println("index=$index, value=$value")
        }
    }

    fun demoConvert() {
        val array = arrayOf("A", "B", "C", "B", "D", "A")

        val list = array.toList()
        list.joinToString().also { println(it) }

        // ArraysUtilJVM.asListへ処理を委譲
        // 内部でjava.util.Arrays.asListを呼び出している
        val list2 = array.asList()

        val set = array.toSet()
        set.joinToString().also { println(it) }

    }

    fun demoToString() {
        val array = arrayOf("A", "B", "C", "D")
        array.toString().also { println(it) }
        // [Ljava.lang.String;@214c265e
        array.joinToString().also { println(it) }
        // A, B, C, D
        array.contentToString().also { println(it) }
        // [A, B, C, D]
    }

}