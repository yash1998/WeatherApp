package com.example.weatherapp.presentation.models

import androidx.annotation.DrawableRes
import com.example.weatherapp.R

data class BriefWeather(
    val cityName: String,
    val avgTemp: Double,
    val briefWeatherDesc: String,
    @DrawableRes val weatherIcon: Int
)