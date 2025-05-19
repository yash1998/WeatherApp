package com.example.weatherapp.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Forecast(
    @SerialName("forecastday")
    val forecastday: List<ForecastDay>
)