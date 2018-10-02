package com.example.exercise.assertj

import com.example.exercise.testing.TestFileUtils
import com.example.exercise.testing.TestFileUtils.LINE_SEPARATOR
import org.assertj.core.api.Assertions.*
import org.junit.*
import org.junit.rules.TestName

class FileExerciseTests {

    @Rule
    @JvmField
    val testName: TestName = TestName()

    @Before
    fun setup() {
        println("setup:${testName.methodName}")
    }

    @After
    fun tearDown() {
        println("tearDown")
    }

    @Test
    fun `test file example`() {
        val contents = listOf("first line", "2nd line", "3rd line")
        val file = TestFileUtils.createTestTempFile(contents)

        assertThat(file).exists().isFile().isAbsolute()
        assertThat(file.name).startsWith("test_").endsWith(".txt")

        // ファイルの内容を検証
        assertThat(contentOf(file))
                .hasLineCount(3)
                .startsWith("first line")
                .contains("2nd line")
                .endsWith("3rd line$LINE_SEPARATOR")  //末尾の改行が必要
    }

    @Test
    fun `test file example 2`() {
        val file = TestFileUtils.getResourceFile("test.txt").toFile()

        assertThat(file)
                .exists()
                .isFile()
                .isAbsolute()
                .hasName("test.txt")

        assertThat(contentOf(file))
                .hasLineCount(3)
                .startsWith("first line")
                .contains("2nd line")
                .endsWith("3rd line")
    }

}