package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.model.WeatherModel
import retrofit2.Response

interface IWeatherRepository {

    suspend fun getWeekWeatherForecast(lat: Double, lng: Double): Response<WeatherModel>
}