package com.example.exercise.testing

import org.assertj.core.api.Assertions.*
import org.junit.*

import org.junit.rules.ExpectedException
import org.junit.rules.TestName
import java.time.LocalDate

class UnitTestExerciseTests {

    private val sut: UnitTestExercise = UnitTestExercise()

    @Rule
    @JvmField
    val expectedException: ExpectedException = ExpectedException.none()

    @Rule
    @JvmField
    val testName: TestName = TestName()

    @Before
    fun setup() {
        println("Before: ${testName.methodName}")
    }

    @After
    fun tearDown() {
        println("After:  ${testName.methodName}")
    }

    @Test
    fun testFormat() {
        val pattern = "yyyy/MM/dd"
        val date = LocalDate.of(2018, 1, 22)
        val actual = sut.format(date, pattern)

        assertThat(actual).isEqualTo("2018/01/22")
    }

    @Ignore("未実装")
    @Test
    fun errorIfNot() {
        expectedException.expect(IllegalArgumentException::class.java)
        expectedException.expectMessage("Exception Message")
    }

}