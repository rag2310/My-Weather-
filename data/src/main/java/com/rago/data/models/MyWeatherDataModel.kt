package com.rago.data.models


import com.google.gson.annotations.SerializedName
import com.rago.domain.models.CurrentWeather
import com.rago.domain.models.Hourly
import com.rago.domain.models.HourlyUnits

data class MyWeatherDataModel(
    @SerializedName("current_weather")
    val currentWeather: CurrentWeather,
    @SerializedName("elevation")
    val elevation: Int,
    @SerializedName("generationtime_ms")
    val generationtimeMs: Double,
    @SerializedName("hourly")
    val hourly: Hourly,
    @SerializedName("hourly_units")
    val hourlyUnits: HourlyUnits,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int
)