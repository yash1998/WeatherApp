package com.example.weatherapp.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class ForecastDay(
    @SerialName("astro")
    val astro: Astro,
    @SerialName("date")
    val date: String,
    @SerialName("date_epoch")
    val dateEpoch: Long,
    @SerialName("day")
    val day: Day
)