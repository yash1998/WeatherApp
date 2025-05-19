package com.example.weatherapp.presentation.screens

import com.example.weatherapp.data.model.WeatherModel

sealed class MainScreenState {
    data object Loading : MainScreenState()
    data class Error(val t: Throwable) : MainScreenState()
    data class Success(val data: WeatherModel) : MainScreenState()
}