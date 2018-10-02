package com.example.exercise.assertj

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.assertj.core.data.MapEntry
import org.junit.Test

fun <T, U> convertToArray(map: Map<T, U>): Array<MapEntry<T, U>> =
    map.asSequence()
       .map { Assertions.entry(it.key, it.value) }
       .toSet()
       .toTypedArray()

class MapExerciseTests {

    @Test
    fun `test map size example`() {
        val actual = mapOf(0 to "A", 1 to "B", 2 to "C", 3 to "D", 4 to "E", 5 to "F", 6 to "G")

        // null or empty
        assertThat(emptyMap<Int, String>())
                .isNullOrEmpty()

        // サイズ
        assertThat(actual)
                .hasSize(7)
                .describedAs("The expected value is 7")

        // サイズ 1 - 7
        assertThat(actual)
                .size()
                .isGreaterThanOrEqualTo(1)
                //.isGreaterThan(1)
                .isLessThanOrEqualTo(7)
                //.isLessThan(7)
                .describedAs("The expected value is 1 - 7")

        // サイズの検証に続けてentryの検証を行う
        assertThat(actual)
                .size()
                .isGreaterThanOrEqualTo(1)
                .isLessThanOrEqualTo(7)
                .returnToMap()
                .allSatisfy { key, value ->
                    // マップの各entryを検証するコード
                    assertThat(key).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(6)
                    assertThat(value).containsPattern("[A-G]")
                }
    }

    @Test
    fun `test map contains example`() {
        val actual = mapOf(
                0 to "alpha", 1 to "beta",
                2 to "gamma", 3 to "delta",
                4 to "epsilon", 5 to "zeta")

        // key
        assertThat(actual).containsKey(0)
        assertThat(actual).containsKeys(0, 2, 4)

        // value
        assertThat(actual).containsValue("alpha")
        assertThat(actual).containsValues("alpha", "beta", "gamma")

        // entry
        assertThat(actual).containsEntry(0, "alpha")
        // 複数指定する
        assertThat(actual).contains(
            entry(0, "alpha"),
            entry(1, "beta"),
            entry(2, "gamma"))

        // 期待値がすべて含まれている
        val expected = mapOf(0 to "alpha", 2 to "gamma", 4 to "epsilon")
        assertThat(actual).containsAllEntriesOf(expected)
    }

    @Test
    fun `test map contains example2`() {
        val actual = mapOf(
                0 to "alpha", 1 to "beta",
                2 to "gamma", 3 to "delta",
                4 to "epsilon", 5 to "zeta").also { println(it) }

        // 並びが逆順の実際値
        val revActual = actual.toSortedMap(Comparator.reverseOrder()).also { println(it) }

        val expectedAnyEntries = convertToArray(mapOf(0 to "alpha", 1 to "beta", 2 to "gamma"))
        val expectedAnyWithUnknownEntries = convertToArray(mapOf(0 to "alpha", 6 to "eta", 7 to "theta"))
        val expectedAllEntries = convertToArray(mapOf(0 to "alpha", 1 to "beta", 2 to "gamma", 3 to "delta", 4 to "epsilon", 5 to "zeta"))

        // 期待値がすべて含まれている
        // java.util.Map.Entry
        // MapAssert<KEY, VALUE> contains(Map.Entry<? extends KEY, ? extends VALUE>... entries)
        assertThat(actual).contains(*expectedAnyEntries)
        // これはAssertionError
        // assertThat(actual).contains(*expectedAnyEntries)

        // 期待値のいずれかが含まれている
        // MapAssert<KEY, VALUE>  containsAnyOf(Map.Entry<? extends KEY, ? extends VALUE>... entries)
        assertThat(actual).containsAnyOf(*expectedAnyWithUnknownEntries)

        // 実際値と期待値は同じentry、順番は無視
        // Verifies that the actual map contains only the given entries and nothing else, in any order.
        // MapAssert<KEY, VALUE> containsOnly(Map.Entry<? extends KEY, ? extends VALUE>... entries)
        assertThat(actual).containsOnly(*expectedAllEntries)
        // これはAssertionError
        // assertThat(actual).containsOnly(*expectedAnyEntries)
        assertThat(revActual).containsOnly(*expectedAllEntries)

        // Verifies that the actual map contains only the given entries and nothing else, in order
        // This assertion should only be used with maps that have a consistent iteration order (i.e. don't use it with
        // {@link java.util.HashMap}, prefer {@link #containsOnly(java.util.Map.Entry...)} in that case).

        // 順番も同じである必要がある
        // MapAssert<KEY, VALUE> containsExactly(Map.Entry<? extends KEY, ? extends VALUE>... entries)
        assertThat(actual).containsExactly(*expectedAllEntries)
        // これはAssertionError
        // assertThat(revActual).containsExactly(*expectedAllEntries)
    }

}