package com.example.exercise.assertj

import org.assertj.core.api.Assertions.*
import org.junit.*
import org.junit.rules.TestName
import java.io.IOException

class SomeKindException(
    message: String,
    cause: Throwable?,
    val line: Int?,
    val column: Int?): Exception(message, cause) {

    constructor(message: String): this(message, null, null, null)
    constructor(message: String, cause: Throwable): this(message, cause, null, null)
    constructor(message: String, line: Int, column: Int): this(message, null, line, column)
    constructor(cause: Throwable): this(cause.toString(), cause, null, null)

    override fun toString(): String {
        return "message:$message, line:$line, column:$column, cause:${cause.toString()} "
    }
}

class ExceptionExerciseTests {

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
    fun `test exception example`() {
        assertThatThrownBy {
            // ここに例外をスローするコードを記述
            throw IOException("test io exception")
        }
        .isInstanceOf(IOException::class.java)
        .hasMessage("test io exception")

        // An alternative syntax is provided as some people find it more natural
        assertThatExceptionOfType(IOException::class.java).isThrownBy {
            // code that should throw an exception
            // ここにIOExceptionをスローするテストコードを記述
            throw IOException("test io exception")
        }
        .withMessage("test io exception")
        .withNoCause()
    }

    @Test
    fun `test exception with cause example`() {
        // An alternative syntax is provided as some people find it more natural
        assertThatExceptionOfType(SomeKindException::class.java).isThrownBy {
            // ここにカスタム例外をスローするテストコードを記述
            val ex = IOException("test IO exception")
            throw SomeKindException("test custom exception", ex)
        }
        .withMessage("test custom exception")
        .withCause(IOException("test IO exception"))
    }

    @Test
    fun `test specific exception example`() {
        assertThatNullPointerException().isThrownBy {
            // ここにNullPointerExeptionをスローするテストコードを記述
            throw NullPointerException("test nullpointer exception")
        }
        .withMessage("test NP exception")
    }

    @Test
    fun `test custom exception example`() {

        catchThrowableOfType({
            // ここにカスタム例外をスローするテストコードを記述
            throw SomeKindException("test custom exception", 10, 75)
        }, SomeKindException::class.java)
        .also { exception ->
            assertThat(exception).hasMessage("test custom exception")
            assertThat(exception.line).isEqualTo(10)
            assertThat(exception.column).isEqualTo(75)
        }

    }

    // 例外はスローされないことをテスト
    @Test
    fun `test exception not expected example`() {
        assertThatCode {
            //例外をスローしないテストコードを記述
            val result = 1 / 0
        }
        .describedAs("not to raise a throwable")
        .doesNotThrowAnyException()
    }

}