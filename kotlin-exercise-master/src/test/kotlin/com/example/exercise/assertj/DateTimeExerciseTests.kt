package com.example.exercise.assertj

import org.assertj.core.api.Assertions.*
import org.junit.*
import org.junit.rules.TestName
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class DateTimeExerciseTests {

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

    // 日付時刻の検証
    @Test
    fun `test LocalDateTime examples`() {
        var actual: LocalDateTime = LocalDateTime.of(2018, 1, 27, 22, 46, 51)

        // 一致
        assertThat(actual).isEqualTo(LocalDateTime.of(2018, 1, 27, 22, 46, 51))
        // 文字列表現を使った検証
        assertThat(actual).isEqualTo("2018-01-27T22:46:51")

        // 一部分をtruncateして検証
        // hours以下を切り落として比較 yyyy-MM-dd
        assertThat(actual).isEqualToIgnoringHours(LocalDateTime.of(2018, 1, 27, 13, 11, 25))
        // minutes以下を切り落として比較 yyyy-MM-dd HH
        assertThat(actual).isEqualToIgnoringMinutes(LocalDateTime.of(2018, 1, 27, 22, 15, 31))
        // seconds以下を切り落として比較 yyyy-MM-dd HH:mm
        assertThat(actual).isEqualToIgnoringSeconds(LocalDateTime.of(2018, 1, 27, 22, 46, 1))

        // 境界の検証
        // 境界値は含まない actual > expected
        assertThat(actual).isAfter("2018-01-26T23:59:59")
        assertThat(actual).isAfter(LocalDateTime.of(2018, 1, 26, 23, 59, 59))

        // 境界の検証
        // 境界値は含まない actual < expected
        assertThat(actual).isBefore("2018-01-28T00:00:00")
        assertThat(actual).isBefore(LocalDateTime.of(2018, 1, 28, 0, 0, 0))

        // 範囲の検証
        // 境界値は含む  actual >= startInclusive & actual <= endInclusive
        assertThat(actual).isBetween("2018-01-26T23:59:59", "2018-01-28T00:00:00")
    }

    @Test
    fun `test LocalDateTime closeTo examples`() {
        // 範囲の検証
        // expectedの前後1時間以内 2018-01-26 23:00:00 - 2018-01-27 01:00:00
        val actual = LocalDateTime.of(2018, 1, 26, 22, 0, 1)
        assertThat(actual).isCloseTo("2018-01-27T00:00:00", within(1, ChronoUnit.HOURS))
    }

    // 日付の検証
    @Test
    fun `test LocalDate examples`() {
        var actual: LocalDate = LocalDate.of(2018, 1, 27)

        // 一致、不一致
        assertThat(actual).isEqualTo("2018-01-27")
        assertThat(actual).isNotEqualTo("2018-01-28")

        assertThat(actual).isEqualTo(LocalDate.of(2018, 1, 27))
        assertThat(actual).isNotEqualTo(LocalDate.of(2018, 1, 28))

        // 境界は含まない actual > expected
        assertThat(actual).isAfter("2018-01-26")
        // 境界を含む actual >= expected
        assertThat(actual).isAfterOrEqualTo("2018-01-27")

        // 境界は含まない actual < expected
        assertThat(actual).isBefore("2018-01-28")
        // 境界を含む actual <= expected
        assertThat(actual).isBeforeOrEqualTo("2018-01-27")

        // 範囲の検証
        assertThat(actual).isBetween("2018-01-27", "2018-01-27")
        assertThat(actual).isBetween("2018-01-01", "2018-01-31")

        assertThat(actual).isIn("2018-01-25", "2018-01-27", "2018-01-29")

        val list = arrayOf("2018-01-26", "2018-01-28", "2018-01-30")
        assertThat(actual).isNotIn(list)
    }

    @Test
    fun `test LocalDate closeTo examples`() {

        // 範囲の検証
        // expectedの前後5日の範囲 2018-01-26 - 2018-02-5
        var actual = LocalDate.of(2018, 1, 27)
        assertThat(actual).isCloseTo("2018-01-31", within(5, ChronoUnit.DAYS))

        // expectedの前後1カ月以内
        // 期待する範囲は 2017-12-01 - 2018-02-01
        // 実際には 2017-11-02 - 2018-03-01
        actual = LocalDate.of(2017, 12, 1)
        assertThat(actual).isCloseTo("2018-01-01", within(1, ChronoUnit.MONTHS))

        assertThat(actual).isCloseTo("2018-01-01", within(1, ChronoUnit.MONTHS))

        val exptected = LocalDate.of(2018, 1, 1)
        val expectedFrom = exptected.minusMonths(1).also { println(it) }
        val expectedTo = exptected.plusMonths(1).also { println(it) }
        assertThat(actual).isBetween(expectedFrom, expectedTo)
    }

}