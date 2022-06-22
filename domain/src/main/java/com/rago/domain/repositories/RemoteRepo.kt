package com.rago.domain.repositories

import com.rago.domain.models.MyWeather
import kotlinx.coroutines.flow.Flow

interface RemoteRepo {
    fun getMyWeather(): Flow<MyWeather>
}