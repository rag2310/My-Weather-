package com.rago.domain.models

import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,
    val time: List<String>
)