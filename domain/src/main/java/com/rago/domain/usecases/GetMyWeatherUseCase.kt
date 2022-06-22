package com.rago.domain.usecases

import com.rago.domain.models.MyWeather
import com.rago.domain.repositories.RemoteRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyWeatherUseCase @Inject constructor(
    private val apiRepo: RemoteRepo
) : FlowUseCase<MyWeather> {
    override fun execute(): Flow<MyWeather> {
        return apiRepo.getMyWeather()
    }
}