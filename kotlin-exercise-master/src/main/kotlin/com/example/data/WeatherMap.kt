package com.example.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class WeatherMap constructor(
    val id: Long,
    val name: String,
    val cod: Int,
    val base: String,
    val dt: Int,
    val coord: Coord,
    val weather: Array<Weather>,
    val main: `Main`,
    val wind: Wind,
    val clouds: Clouds,
    val sys: Sys
) {
    data class Coord(
        val lon: Double,
        val lat: Double)

    data class Weather(
        val id: Long,
        val main: String,
        val description: String,
        val icon: String
    )
    data class `Main`(
        val temp: Double,
        val pressure: Double,
        val humidity: Int,
        @JsonProperty("temp_min")
        val tempMin: Double,
        @JsonProperty("temp_max")
        val tempMax: Double,
        @JsonProperty("sea_level")
        val seaLevel: Double,
        @JsonProperty("grnd_level")
        val grndLevel: Double
    )
    data class Wind(
        val speed: Double,
        val deg: Int
    )
    data class Clouds(
        val all: Int
    )
    data class Sys(
        val message: Double,
        val country: String,
        val sunrise: Int,
        val sunset: Int
    )
}