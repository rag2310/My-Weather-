package com.rago.data.apiservice

import com.rago.data.models.MyWeatherDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("v1/forecast")
    fun getMyWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = "temperature_2m",
        @Query("current_weather") currentWeather: Boolean = true
    ): Call<MyWeatherDataModel>
}