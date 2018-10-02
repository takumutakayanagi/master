package com.example.exercise.exception

fun fail(message: String): Nothing {
    throw MyException(message)
}

// with lambda expression
fun fail(message: () -> Any): Nothing {
    throw MyException(message().toString())
}

fun main(args: Array<String>) {
    ExceptionExercise().tryAll()
}

/**
 * 例外
 *
 * [exceptions] (https://kotlinlang.org/docs/reference/exceptions.html)
 */
class ExceptionExercise {

    fun tryAll() {
        throwCustomException()

        throwExceptionWhenNameIsNull("tom")
        throwExceptionWhenNameIsNull(null)

        throwExceptionWhenNameIsNull2("tom")
        throwExceptionWhenNameIsNull2(null)

        throwCustomExceptionWhenNameIsNull("tom")
        throwCustomExceptionWhenNameIsNull(null)

        throwCustomExceptionWhenNameIsNull2("tom")
        throwCustomExceptionWhenNameIsNull2(null)
    }

    fun throwCustomException() {
        try {
            throw MyException("Raise an my Exception")
        } catch (e: Exception) {
            println(e)
        }

        try {
            throw MyException("Raise an my Exception", 999)
        } catch (e: Exception) {
            println(e)
        }

        try {
            val other = RuntimeException("Raise an RuntimeException")
            throw MyException(other)
        } catch (e: Exception) {
            println(e)
        }
    }

    // errorはkotlinの標準ライブラリ
    fun throwExceptionWhenNameIsNull(name: String?) {
        try {
            // IllegalStateException
            name ?: error("Name required")
        } catch(e: Exception) {
            println(e)
        }
    }

    // requireNotNullはkotlinの標準ライブラリ
    fun throwExceptionWhenNameIsNull2(name: String?) {
        try {
            // IllegalArgumentException
            requireNotNull(name) { "Name required" }
        } catch (e: Exception) {
            println(e)
        }
    }

    fun throwCustomExceptionWhenNameIsNull(name: String?) {
        try {
            name ?: fail("Name required")
        } catch (e: Exception) {
            println(e)
        }
    }

    fun throwCustomExceptionWhenNameIsNull2(name: String?) {
        try {
            name ?: fail { "Name required" }
        } catch (e: Exception) {
            println(e)
        }
    }

}