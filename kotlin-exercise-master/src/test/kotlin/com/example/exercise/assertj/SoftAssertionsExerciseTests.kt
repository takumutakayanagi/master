package com.example.exercise.assertj

import org.assertj.core.api.AutoCloseableSoftAssertions
import org.assertj.core.api.JUnitSoftAssertions
import org.assertj.core.api.SoftAssertions
import org.junit.*
import org.junit.rules.TestName

class SoftAssertionsExerciseTests {

    @Rule
    @JvmField
    val softlyRule: JUnitSoftAssertions = JUnitSoftAssertions()

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

    // 基本的な使い方
    @Test
    fun `test softly assertion examples`() {
        SoftAssertions().apply {
            assertThat("abc").describedAs("abc != ABC").isEqualTo("ABC")
            assertThat(1).describedAs("1 != 2").isEqualTo(2)
        }.assertAll()
    }

    // ラムダ式
    @Test
    fun `test softly assertion lambda examples`() {
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat("abc").describedAs("abc != ABC").isEqualTo("ABC")
            softly.assertThat(1).describedAs("1 != 2").isEqualTo(2)
            // softly.assertAll() 不要
        }
    }

    // use関数
    @Test
    fun `test softly assertion using use examples`() {
        AutoCloseableSoftAssertions().use { softly ->
            softly.assertThat("abc").describedAs("abc != ABC").isEqualTo("ABC")
            softly.assertThat(1).describedAs("1 != 2").isEqualTo(2)
            // softly.assertAll() 不要
        }
    }

    // JUnit Rule
    @Test
    fun `test softly assertion using Junit Rule example`() {
        softlyRule.assertThat("abc").describedAs("abc != ABC").isEqualTo("ABC")
        softlyRule.assertThat(1).describedAs("1 != 2").isEqualTo(2)
        // assertAll() は不要
    }

}