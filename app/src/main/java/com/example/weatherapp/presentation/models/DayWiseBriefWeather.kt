package com.example.weatherapp.presentation.models

import androidx.annotation.DrawableRes


data class DayWiseBriefWeather(
    val dayName: String,
    val avgTemp: Double,
    @DrawableRes val weatherIcon: Int
)