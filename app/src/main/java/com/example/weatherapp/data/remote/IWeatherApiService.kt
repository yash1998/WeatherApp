package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.utils.API_KEY
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherApiService {

    @GET("current.json")
    suspend fun getCurrentWeatherForecast(
        @Query("q") latLng: String,
        @Query("hour") hour: Int = 24,
        @Query("key") apiKey: String = API_KEY,
        @Query("aqi") aqi: String = "no"
    )

    @GET("forecast.json")
    suspend fun getWeekWeatherForecast(
        @Query("q") latLng: String,
        @Query("days") numDays: Int = 7,
        @Query("hour") hour: Int = 24,
        @Query("key") apiKey: String = API_KEY,
        @Query("aqi") aqi: String = "no"
    ): Response<WeatherModel>

}