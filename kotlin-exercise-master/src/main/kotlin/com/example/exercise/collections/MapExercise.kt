package com.example.exercise.collections

fun main(args: Array<String>) {
    MapExercise().tryAll()
}

class MapExercise {

    fun tryAll() {
        readOnlyMap()
        mutableMap()

        demoNullFilter()

        demoMapWithEach()
        demoConvert()
    }

    private fun readOnlyMap() {
        val map = mapOf("A" to 1, "B" to 2, "C" to 3)
        // map.put("D", 4) compile error
        println(map)
        // map.clear() compile error
    }

    private fun mutableMap() {
        val map = mutableMapOf("A" to 1, "B" to 2, "C" to 3)
        // この書き方より
        map.put("D", 4)
        // こちらの書き方が推奨される
        map["C"] = 9
        println(map)
        map.clear()
    }

    private fun demoNullFilter() {
        val nullableMap: Map<String, Int?> = mapOf("A" to 1, "B" to 2, "C" to null, "D" to null, "E" to 5)
        val map: Map<String, Int> =  nullableMap.filterValues { it != null }.mapValues { it.value ?: 0 }
        println(map)
    }

    private fun demoMapWithEach() {
        val map = mapOf("A" to "a", "B" to "b", "C" to "c")

        // Kotlin 1.1
        map.onEach { entry ->
            println("onEach: key=${entry.key}, value=${entry.value}")
        }
        // JVM
        map.forEach { key, value ->
            println("forEach: key=$key, value=$value")
        }
    }

    private fun demoConvert() {
        val map = mapOf("key1" to "value1", "key2" to "value2", "key3" to "value3")

        val list = map.toList()
        list.joinToString().also { println(it) }

        val prop = map.toProperties()
        prop.forEach { t, u ->
            println("prop: key=${t}, value=${u}")
        }
        println("key1=${prop["key1"]}")
    }

}