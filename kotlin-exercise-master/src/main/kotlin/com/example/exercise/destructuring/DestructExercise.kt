package com.example.exercise.destructuring

import com.example.data.Person
import com.example.type.Blood

fun main(args: Array<String>) {
    DestructExercise().tryAll()
}

/**
 *  分解宣言
 *
 *  [multi-declarations] (https://kotlinlang.org/docs/reference/multi-declarations.html)
 */
class DestructExercise {

    fun tryAll() {
        destructOfClass()
        destructOfClasses()
        destructOfMap()
    }

    fun destructOfClass() {
        val person = Person("kevin", 30, Blood.A)

        val (name, age) = person
        println("name:$name, age:$age")

        // unused variables
        val (x, _, z) = person
        println("name:$x, blood:$z")
    }

    fun destructOfClasses() {
        val persons = listOf(
                Person("kevin", 30, Blood.A),
                Person("tom", 41, Blood.B),
                Person("george", 52, Blood.O))

        // in for-loops
        for ((name, age) in persons) {
            println("name:$name, age:$age")
        }

        // in for-loops with unused variables
        for ((x, _, z) in persons) {
            println("name:$x, blood:$z")
        }

        // in Lambdas
        persons.forEach {
            (name, age) -> println("name:$name, age:$age")
        }
    }

    fun destructOfMap() {
        val map = mapOf("A" to "alpha", "B" to "beta", "G" to "gamma")

        // in for-loops
        for ((k, v) in map) {
            println("key:$k, value:$v")
        }

        // in Lambdas
        map.mapValues {
            (k, v) -> println("key;$k, value:$v")
        }
    }

}