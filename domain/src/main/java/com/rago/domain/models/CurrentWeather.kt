package com.rago.domain.models


data class CurrentWeather(
    val temperature: Double,
    val time: String,
    val weatherCode: Int,
    val windDirection: Int,
    val windSpeed: Int
)