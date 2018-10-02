package com.example.exercise.function

import com.example.exercise.lambda.intSum

fun main(args: Array<String>) {
    OtherFunctionExercise().tryAll()
}

class OtherFunctionExercise {

    fun tryAll() {
        // 同一パッケージ内の拡張関数はimportせずに使える
        println("inner: ${10.extSum(5)}")

        // パッケージ外の拡張関数はimportする
        println("outer: ${10.intSum(6)}")
    }

}