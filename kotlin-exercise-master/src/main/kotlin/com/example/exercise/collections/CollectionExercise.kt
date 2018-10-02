package com.example.exercise.collections

fun main(args: Array<String>) {
    CollectionExercise().tryAll()
}

/**
 * コレクション
 *
 * [collections] (https://kotlinlang.org/docs/reference/collections.html)
 */
class CollectionExercise {

    fun tryAll() {
        readOnlyList()
        mutableList()

        readOnlySet()
        mutableSet()

        demoNullFilter()

        demoCollectionWithEach()

        demoConvert()

        demoArrayList()
    }

    fun readOnlyList() {
        val list1 = listOf(1, 2, 3)
        // list.add(4) compile error
        println(list1)
        // list.clear() compile error

        val list2: List<String> = arrayOf("A", "B", "C").toList()
        // list2.add("D") compile error
        println(list2)
        // list2.clear() compile error

        val list3 = listOf("A" to "a", "B" to "b", "C" to "c")
        println(list3)
    }

    fun mutableList() {
        val list1 = mutableListOf(1, 2, 3)
        list1.add(4)
        println(list1)
        println(list1.first() == 1)
        println(list1.last() == 4)
        list1.clear()

        val list2: MutableList<String> = arrayOf("A", "B", "C").toMutableList()
        list2.add("D")
        println(list2.first() == "A")
        println(list2.last() == "D")
        list2.clear()
    }

    fun readOnlySet() {
        val set1 = setOf("d", "c", "b")
        // set1.add("a") compile error
        println(set1)
        // set1.clear() compile error

        val set2: Set<String> = arrayOf("D", "C", "B").toHashSet()
        // set2.add("A") compile error
        println(set2)
        // set2.clear() compile error
    }

    fun mutableSet() {
        val set1 = mutableSetOf("d", "c", "b")
        set1.add("a")
        println(set1)
        println(set1.first() == "d")
        println(set1.last() == "a")
        set1.clear()

        val set2: MutableSet<String> = arrayOf("D", "C", "B").toMutableSet()
        set2.add("A")
        println(set2)
        println(set2.first() == "D")
        println(set2.last() == "A")
        set2.clear()
    }

    fun demoNullFilter() {
        val nullableList: List<Int?> = listOf(1, 2, null, 4, null, 6)
        val list = nullableList.filterNotNull()
        println(list)

        val nullableSet: Set<String?> = setOf("A", "B", null, "D", null, "F")
        val set = nullableSet.filterNotNull()
        println(set)
    }

    fun demoCollectionWithEach() {
        val list = listOf("A", "B", "C", "D", "E")

        // Kotlin 1.1
        // 戻り値はList<String>
        val r1 = list.onEach { println(it) }

        // 戻り値はUnit
        val r2 = list.forEach { println(it) }
    }

    fun demoConvert() {
        val list = listOf("A", "B", "C", "D")
        val set = list.toSet()

        list.joinToString().also { println(it) }
        set.joinToString().also { println(it) }

        val mutableList: MutableCollection<String> = mutableListOf()
        val mutableSet: MutableCollection<String> = mutableSetOf()

        list.toCollection(mutableList)
        list.toCollection(mutableSet)

        mutableList.add("E")
        mutableSet.add("F")

        mutableList.joinToString().also { println(it) }
        mutableSet.joinToString().also { println(it) }
    }

    fun demoArrayList() {
        println("*** demoArrayList ***")
        // java.util.ArrayList<E>
        val list: MutableList<String> = ArrayList<String>()
        list.add("A")
        list.add("B")
        list.add("C")
        list.add("D")
        list.joinToString().also { println(it) }
    }

}