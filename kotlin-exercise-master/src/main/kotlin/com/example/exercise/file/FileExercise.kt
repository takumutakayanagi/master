package com.example.exercise.file

import com.github.kittinunf.fuel.core.requests.write
import com.github.kittinunf.fuel.core.requests.writeln
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.URL
import java.util.*

fun main(args: Array<String>) {
    FileExercise().tryAll()
}

/**
 * ファイル
 *
 */
class FileExercise {

    companion object {
        fun loader(name: String): File {
            val url: URL = ClassLoader.getSystemResource(name)
            println(url)
            return File(url.file)
        }
    }

    fun tryAll() {
        demoFileReadLines()
        demoFileUseLines()
        demoFileForEachLine()

        demoReaderWithReadLines()
        demoReaderWithUseLines()
        demoReaderWithForEachLine()

        demoFileNotFound()
        demoPropertyFileReader()

        demoFileWrite()
    }

    // File.readLines(charset: Charset = Charsets.UTF_8): List<String>
    // readLinesは内部でFile#forEachLine → Reader.forEachLine → Reader.useLines → use関数を
    // 使用しているのでStreamを自動的に閉じる
    fun demoFileReadLines() {
        val file = File("test.txt")
        println("File#ReadLines: ${file.absoluteFile}")

        file.readLines()
            .forEach { println(it) }

        file.readLines(charset = Charsets.UTF_8)
            .joinToString(":") { it.trim() }
            .also { text -> println("$text") }
    }

    // <T> File.useLines(charset: Charset = Charsets.UTF_8, block: (Sequence<String>) -> T): T
    // useLinesは内部でuse関数を使用しているのでStreamを自動的に閉じる
    fun demoFileUseLines() {
        val file = File("test.txt")
        println("File#UseLines: ${file.absoluteFile}")

        file.useLines {
            it.map { it.toLowerCase() }.forEach { println(it) }
        }

        file.useLines(charset = Charsets.UTF_8, block = { line ->
            line.map { it.length }.sorted().forEach { println(it) }
        })
    }

    // File.forEachLine(charset: Charset = Charsets.UTF_8, action: (line: String) -> Unit): Unit
    // forEachLineは内部でReader.useLines -> use関数を使用しているのでStreamを自動的に閉じる
    fun demoFileForEachLine() {
        val file = File("test.txt")
        println("File#ForEachLine: ${file.absoluteFile}")

        file.forEachLine {
            println(it)
        }

        file.forEachLine(charset = Charsets.UTF_8, action = { line ->
            println(line)
        })
    }

    fun demoReaderWithReadLines() {
        val file = File("test.txt")
        println("Reader#ReadLines: ${file.absoluteFile}")

        file.reader().readLines().forEach { println(it) }

        file.reader().readLines().joinToString(separator = ":", prefix = "[", postfix = "]", transform = {
            it.trim()
        })
        .also { text -> println("$text") }
    }

    fun demoReaderWithUseLines() {
        val file = File("test.txt")
        println("Reader#UseLines: ${file.absoluteFile}")

        // useLines
        file.reader().useLines {
            it.map { it.toLowerCase() }.forEach { println(it) }
        }

        // block: (Sequence<String>) -> T
        file.reader().useLines(block = {
            println("sorted")
            it.sortedBy { it.length }.forEach { println(it) }
            println()
        })
    }

    fun demoReaderWithForEachLine() {
        val file = File("test.txt")
        println("Reader#ForEachLine: ${file.absoluteFile}")

        // forEachLine
        file.reader().forEachLine {
            println(it)
        }

        // action: (String) -> Unit
        file.reader().forEachLine(action = { line ->
            println(line)
        })
    }

    fun demoFileNotFound() {
        try {
            loader("unknown.txt")
        } catch (e: Exception) {
            println(e)
        }
    }

    fun demoPropertyFileReader() {
        val prop = Properties().apply {
            // fun <T : Closeable?, R> T.use(block: (T) -> R): R
            FileInputStream("src/main/resources/config/config.properties").use { reader ->
                load(reader)
            }
        }
        // useを使うとStreamを自動的に閉じてくれる
        println(prop.toList().joinToString(" , "))
    }

    fun demoFileWrite() {
        val textLines = listOf("first line", "2nd line", "3rd line")

        val file = File("newFile,txt").apply {
            if (exists()) delete()
        }
        FileOutputStream(file).use { writer ->
            textLines.forEach {
                writer.write(it)
                writer.writeln()
            }
        }
    }

}