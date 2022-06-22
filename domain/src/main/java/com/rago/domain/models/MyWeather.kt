package com.rago.domain.models

data class MyWeather(
    val currentWeather: CurrentWeather,
    val elevation: Int,
    val generationTimeMs: Double,
    val hourly: Hourly,
    val hourlyUnits: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val utcOffsetSeconds: Int
)