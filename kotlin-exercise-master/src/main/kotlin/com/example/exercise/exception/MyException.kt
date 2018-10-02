package com.example.exercise.exception

class MyException(message: String, cause: Throwable?, private var code: Int?): Exception(message, cause) {

    constructor(message: String): this(message, null, null)
    constructor(message: String, code: Int): this(message, null, code)
    constructor(cause: Throwable): this(cause.toString(), cause, null)

    override fun toString(): String {
        return "message:$message, code:$code, cause:${cause.toString()} "
    }
}