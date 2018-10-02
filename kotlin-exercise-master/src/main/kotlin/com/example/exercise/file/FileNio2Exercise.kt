package com.example.exercise.file

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun main(args: Array<String>) {
    FileNio2Exercise().tryAll()
}

class FileNio2Exercise {

    fun tryAll() {
        demoReadLines()
        demoLines()
    }

    fun demoReadLines() {
        val path: Path? = Paths.get("test.txt")
        path?.let {
            println(it.toAbsolutePath().toString())
        }

        Files.readAllLines(path)
                .forEach { println(it) }

        Files.readAllLines(path, Charsets.UTF_8)
                .forEach { println(it) }
    }

    fun demoLines() {
        val path: Path? = Paths.get("test.txt")

        Files.lines(path)
                .forEach { println(it) }

        val byLength: Comparator<String> = Comparator { left, right ->
            left.length.compareTo(right.length)
        }

        Files.lines(path).map { it.toLowerCase() }
                .sorted(byLength)
                .forEach { println(it) }
    }

}