package com.example.exercise.logging

import org.slf4j.LoggerFactory
import java.lang.invoke.MethodHandles
import java.time.LocalDateTime

fun <T> logger(clazz: Class<T>) = LoggerFactory.getLogger(clazz)!!

fun main(args: Array<String>) {
    LoggingExercise().tryAll()
}

/**
 * ロギング
 *
 */
class LoggingExercise {
    private val log = logger(LoggingExercise::class.java)
    //private val log = logger(MethodHandles.lookup().lookupClass())

    init {
        log.debug("init log")
    }

    fun tryAll() {
        logOutput()
        logOutputWithException()
    }

    fun logOutput() {
        val now = LocalDateTime.now()
        log.debug("now:{}", now)
        log.info("now:{}", now)
        log.warn("now:{}", now)
        log.error("now:{}", now)
    }

    fun logOutputWithException() {
        val e = RuntimeException("Something wrong happened")
        log.error("Raise an exception", e)
    }

}