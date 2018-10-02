package com.example.exercise.assertj

import com.example.exercise.testing.TestFileUtils.LINE_SEPARATOR
import org.assertj.core.api.Assertions.*
import org.junit.*
import org.junit.rules.TestName

/**
 * Int, String, Boolean
 *
 */
class BasicExerciseTests {

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

    // 数値型のassertion
    @Test
    fun `test Int examples`() {
        var actual: Int?
        var expected: Int?

        // 一致、不一致の検証
        actual = 10
        assertThat(actual).isEqualTo(10)
        assertThat(actual).isNotEqualTo(20)

        // 境界の検証
        assertThat(actual).isGreaterThan(9)
        assertThat(actual).isGreaterThanOrEqualTo(10)

        assertThat(actual).isLessThan(11)
        assertThat(actual).isLessThanOrEqualTo(10)

        // 範囲内の検証
        actual = 10
        expected = 13
        assertThat(actual).isCloseTo(expected, within(5)).describedAs("The expected value is 8 - 18")
        assertThat(actual).isCloseTo(expected, byLessThan(5)).describedAs("The expected value is 9 - 17")

        // 範囲外の検証
        actual = 7
        expected = 13
        assertThat(actual).isNotCloseTo(expected, within(5)).describedAs("The expected value is not 8 - 18")
        assertThat(actual).isNotCloseTo(expected, byLessThan(5)).describedAs("The expected value is not 9 - 17")
    }

    // 文字列型の検証
    @Test
    fun `test String examples`() {
        var actual: String? = null

        // null
        assertThat(actual).isNull()

        actual = "abc"
        assertThat(actual).isNotNull()

        // 空文字
        actual = ""
        assertThat(actual).isEmpty()

        // 半角スペース
        actual = "    "
        assertThat(actual).isBlank()

        // nullか空文字
        actual = ""
        assertThat(actual).isNullOrEmpty()

        // 一致、不一致、部分一致、部分不一致
        actual = "abc"
        assertThat(actual).isEqualTo("abc")
        assertThat(actual).isNotEqualTo("ABC")

        assertThat(actual).startsWith("a")
        assertThat(actual).endsWith("c")
        assertThat(actual).contains("b")

        assertThat(actual).doesNotStartWith("x")
        assertThat(actual).doesNotEndWith("x")
        assertThat(actual).doesNotContain("x")

        actual = "123456"

        assertThat(actual).containsOnlyDigits()

        actual = "abc"

        // 大文字小文字の違いを無視
        assertThat(actual).isEqualToIgnoringCase("ABC")
        // 半角スペースを無視
        assertThat(actual).isEqualToIgnoringWhitespace("a b c")
        // 改行を無視
        assertThat(actual).isEqualToIgnoringNewLines("a${LINE_SEPARATOR}b${LINE_SEPARATOR}c")

        // 正規表現
        actual = "aBc"
        assertThat(actual).containsPattern("[a-zA-Z]".toPattern())
    }

    @Test
    fun `test String multi lines examples`() {
        val actual: String = """
            |abc
            |123
            |ddd
        """.trimMargin()
        println(actual)

        assertThat(actual).contains("abc")
        assertThat(actual).hasLineCount(3)
    }

    // 真偽値の検証
    @Test
    fun `test Boolean examples`() {
        var actual:Boolean = true

        assertThat(1 == 1).isTrue()
        assertThat(1 == 2).isFalse()

        assertThat(actual).isEqualTo(true)
        assertThat(actual).isNotEqualTo(false)
    }

    // 配列の検証
    @Test
    fun `test array examples`(){
        val actual = arrayOf("A", "B", "C")

        assertThat(actual).contains("A", "B")
        assertThat(actual).doesNotContain("D", "E")
    }

}