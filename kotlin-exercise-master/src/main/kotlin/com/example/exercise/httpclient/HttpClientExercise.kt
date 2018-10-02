package com.example.exercise.httpclient

import com.example.data.WeatherMap
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs

fun main(args: Array<String>) {
    HttpClientExercise().tryAll()
}

/**
 * Fuel
 *
 * [Github - Fuel] (https://github.com/kittinunf/Fuel)
 */
class HttpClientExercise {

    companion object {
        private const val SAMPLE_URL: String = "http://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22"
    }

    fun tryAll() {
        getApiWithAsyncMode()
        getApiWithBlocking()
    }

    // Async mode
    fun getApiWithAsyncMode() {
        println("Async mode api call before")
        SAMPLE_URL.httpGet().responseString { _, _, result ->
            when(result) {
                is Result.Success -> {
                    val rawData = result.get()
                    rawData.let {
                        val mapper = jacksonObjectMapper()
                        val jsonData = mapper.readValue<WeatherMap>(rawData)
                        println(jsonData)
                    }
                }
                is Result.Failure -> {
                    error("api call faiure: ${result.get()}")
                }
            }
        }
        .timeout(10)
        println("Async mode api call after")
    }

    //Blocking mode
    fun getApiWithBlocking() {
        println("Blocking mode api call before")
        val (_, _, result) = SAMPLE_URL.httpGet().responseString()
        val weatherData = when(result) {
            is Result.Success -> {
                result.get().let {
                    val mapper = jacksonObjectMapper()
                    val jsonData = mapper.readValue<WeatherMap>(it)
                    println(jsonData)
                    jsonData //return value.
                }
            }
            is Result.Failure -> {
                error("api call failure: ${result.get()}")
            }
        }
        println("Blocking mode api call after")
    }
}