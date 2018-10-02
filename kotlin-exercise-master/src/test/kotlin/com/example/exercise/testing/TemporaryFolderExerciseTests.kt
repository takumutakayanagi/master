package com.example.exercise.testing

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.*
import org.junit.rules.TemporaryFolder
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDate
import java.util.*

/**
 * リソースファイルを一時ディレクトリへコピーする
 *
 */
fun TemporaryFolder.copyFile(srcFileName: String, dstFileName: String) {
    //val srcPath: Path = TestFileUtils.getResourceFile(srcFileName)
    val srcPath: Path = Paths.get(this.javaClass.classLoader.getResource(srcFileName).toURI())
    val dstPath: Path = Paths.get(root.absolutePath, dstFileName)
    Files.copy(srcPath, dstPath)
}

/**
 * 一時ディレクトリ内にあるファイルのパスを返す
 *
 */
fun TemporaryFolder.getPath(fileName: String): Path =
    Paths.get(root.absolutePath, fileName)

fun Path.dump() {
    Files.readAllLines(this).forEach { println(it) }
}
fun Properties.dump() {
    toList().joinToString(", ").also { println(it) }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class TestDataJson(val name: String, val description: String, val price: Int)

class TemporaryFolderExerciseTests {

    /* クラスルールを適用する場合
    companion object {
        @ClassRule
        @JvmField
        val tempFolder: TemporaryFolder = TemporaryFolder()
    }
    */

    @Rule
    @JvmField
    //@get:Rule
    val tempFolder: TemporaryFolder = TemporaryFolder()

    val rootPath: Path
        get() = tempFolder.root.toPath()

    @Before
    fun setup() {
        println("setup: root=${tempFolder.root}")
        println("rootPath=$rootPath")
    }

    @After
    fun tearDown() {
        println("tear down:")
    }

    @Test
    fun `test file copy`() {
        println("test file copy")

        tempFolder.copyFile("data/template/template.txt","test.txt")

        val testFile = tempFolder.getPath("test.txt").also { println(it.toAbsolutePath().toString()) }

        testFile.dump()

        // 一時ディレクトリにコピーしたファイルなので削除しても問題ない
        testFile.toFile().delete()
    }

    @Test
    fun `test file write`() {
        println("test file write")

        val testFile = tempFolder.newFile("test.txt").toPath().also { println(it.toAbsolutePath().toString()) }

        testFile.dump()

        val textLines = listOf("first line", "2nd line", "3rd line", LocalDate.of(2018, 1, 26).toString())

        Files.write(testFile, textLines)

        testFile.dump()
    }

    @Test
    fun `test file delete`() {
        println("test file delete")

        val testFile: Path = TestFileUtils.getResourceFile("template.txt")

        testFile.dump()

        // 削除するとリソースディレクトリ下のファイルが削除されてしまう
        //testFile.toFile().delete()
    }

    @Test
    fun `test read property file`() {
        println("test read property file")

        val testFile: Path = TestFileUtils.getResourceFile("test.properties", "data/config")

        val prop = Properties().apply {
            Files.newInputStream(testFile).use { reader ->
                load(reader)
            }
        }
        prop.dump()
    }

    @Test
    fun `test read json file`() {
        println("test read json file")

        val testFile: Path = TestFileUtils.getResourceFile("template.json")

        val jsonData: TestDataJson = jacksonObjectMapper().readValue(testFile.toFile())

        jsonData.also { println(it) }
    }

}