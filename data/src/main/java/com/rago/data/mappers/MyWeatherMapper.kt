package com.rago.data.mappers

import com.rago.data.models.MyWeatherDataModel
import com.rago.domain.models.MyWeather
import javax.inject.Inject

class MyWeatherMapper @Inject constructor() {
    fun toMyWeather(myWeather: MyWeatherDataModel): MyWeather {
        return MyWeather(
            myWeather.currentWeather,
            myWeather.elevation,
            myWeather.generationtimeMs,
            myWeather.hourly,
            myWeather.hourlyUnits,
            myWeather.latitude,
            myWeather.longitude,
            myWeather.utcOffsetSeconds
        )
    }
}