package com.rago.myweather.ui.myweather

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rago.domain.models.MyWeather

@Composable
fun MyWeatherScreen(
    viewModel: MyWeatherViewModel
) {
    val weather by viewModel.weather.collectAsState()

    MyWeatherContent(weather)
}

@Preview
@Composable
fun MyWeatherContent(weather: MyWeather? = null) {
    Scaffold(Modifier.fillMaxWidth()) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (weather == null) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else {
                Text("${weather.hourly.time.size} == ${weather.hourly.temperature2m.size}")
            }
        }
    }
}
