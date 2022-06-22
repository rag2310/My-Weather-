package com.rago.data.repo

import com.rago.data.apiservice.ApiService
import com.rago.data.mappers.MyWeatherMapper
import com.rago.data.models.MyWeatherDataModel
import com.rago.domain.models.MyWeather
import com.rago.domain.repositories.RemoteRepo
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val myWeatherMapper: MyWeatherMapper
) : RemoteRepo {
    override fun getMyWeather(): Flow<MyWeather> = callbackFlow {

        val callback = object : Callback<MyWeatherDataModel> {
            override fun onResponse(
                call: Call<MyWeatherDataModel>,
                response: Response<MyWeatherDataModel>
            ) {
                val body = response.body()
                body?.let {
                    println("body ${it.latitude}")
                    trySend(myWeatherMapper.toMyWeather(body))
                }
            }

            override fun onFailure(call: Call<MyWeatherDataModel>, t: Throwable) {
                cancel(t.message!!, t)
            }

        }

        val network = apiService.getMyWeather(12.20, -86.39).also {
            it.enqueue(callback)
        }
        awaitClose {
            network.cancel()
        }
    }
}