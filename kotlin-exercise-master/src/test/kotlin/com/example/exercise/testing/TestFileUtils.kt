package com.example.exercise.testing

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

object TestFileUtils {
    val LINE_SEPARATOR = System.lineSeparator()!!

    fun getResourceFile(fileName: String, dir: String = "data/template") : Path =
        Paths.get(this.javaClass.classLoader.getResource("$dir/$fileName").toURI())

    fun createTestTempFile(contents: List<String>, prefix: String = "test_", suffix: String = ".txt") : File =
        Files.createTempFile(prefix, suffix).toFile().apply {
            //println(this.absoluteFile.toString())
            Files.write(this.toPath(), contents)
            this.deleteOnExit()
    }
}