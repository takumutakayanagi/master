package com.example.exercise.json

import com.example.data.Person
import com.example.data.WeatherMap
import com.example.exercise.logging.logger
import com.example.type.Blood
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File
import java.lang.invoke.MethodHandles

fun main(args: Array<String>) {
    JsonExercise().tryAll()
}

/**
 * JSON
 *
 */
class JsonExercise {
    private val log = logger(MethodHandles.lookup().lookupClass())

    fun tryAll() {
        log.debug("tryAll")
        readFromStringLiteral()
        readFromFile()
        readFromWeatherFile()
        writeFromObject()
    }

    fun readFromStringLiteral() {
        val jsonString = """
            |{
            |  "name": "tom",
            |  "age": 50,
            |  "blood": "A"
            |}""".trimMargin()
        println(jsonString)

        val mapper = jacksonObjectMapper()

        val result = mapper.readValue<Person>(jsonString)
        println(result)
    }

    // デシリアライズ
    fun readFromFile() {
        val jsonFile = File("person.json")
        println(jsonFile.readText())

        val mapper = jacksonObjectMapper()

        //val result = mapper.readValue(jsonFile, Person::class.java)
        //val result = mapper.readValue<Person>(jsonFile)
        val result: Person = mapper.readValue(jsonFile)
        println(result)
    }

    // デシリアライズ
    fun readFromWeatherFile() {
        val jsonFile = File("weather.json")

        val mapper = jacksonObjectMapper()

        val result: WeatherMap = mapper.readValue(jsonFile)
        println(result)
    }

    // シリアライズ
    fun writeFromObject() {
        val jsonFile = File("write_test.json").also { file ->
            if (file.exists()) {
                file.delete()
            }
        }

        val person = Person("george", 50, Blood.O)
        println(person)

        val mapper = jacksonObjectMapper()

        mapper.writeValue(jsonFile, person)
    }

}