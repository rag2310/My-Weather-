package com.rago.myweather.ui.myweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rago.domain.models.MyWeather
import com.rago.domain.usecases.GetMyWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MyWeatherViewModel @Inject constructor(
    private val myWeatherUseCase: GetMyWeatherUseCase
) : ViewModel() {

    val weather: StateFlow<MyWeather?> = flow<MyWeather?> {
        emitAll(myWeatherUseCase.execute())
    }.stateIn(
        scope = viewModelScope,
        initialValue = null,
        started = SharingStarted.Lazily
    )
}