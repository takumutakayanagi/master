package com.example.exercise.assertj

import org.assertj.core.api.Assertions.*
import org.assertj.core.api.Condition
import org.junit.*
import org.junit.rules.TestName
import java.util.function.Predicate

class CollectionExerciseTests {

    companion object {
        private val FRUIT_SET = setOf("apple", "banana", "cherry", "durian", "elderberry")
        // create condition
        private val FRUIT = Condition<String>(Predicate { it -> FRUIT_SET.contains(it) }, "is fruit")
    }

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

    // コレクションの検証
    @Test
    fun `test collection examples`() {
        val actual = listOf("David", "Julie", "Sofia", "McCabe", "Brian")

        assertThat(actual)
                .contains("David", "Julie", "Sofia")
                .doesNotContain("Thomas", "Edmund")
    }

    // コンディションを利用した検証
    @Test
    fun `test condition example`() {
        // using is / isNot
        assertThat("apple").`is`(FRUIT)
        assertThat("broccoli").isNot(FRUIT)

        // Verifying condition on collection elements
        assertThat(setOf("apple", "banana")).are(FRUIT)
        assertThat(setOf("arugula", "broccoli")).areNot(FRUIT)
    }

    // コンディション フィルター
    @Test
    fun `test condition example 2`() {
        val actual = setOf("apple", "banana", "cherry", "durian", "elderberry", "apricot", "mango")

        assertThat(actual)
            .filteredOn(startsWith("a"))
            .containsOnly("apple", "apricot")

        assertThat(actual)
            .filteredOn { it.startsWith("a") }
            .containsOnly("apple", "apricot")

    }

    fun startsWith(letter: String): Condition<String> {
        return Condition(Predicate { it.startsWith(letter) }, "is cond" )
    }

}