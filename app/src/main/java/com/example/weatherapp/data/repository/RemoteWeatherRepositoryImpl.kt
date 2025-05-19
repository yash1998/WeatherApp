package com.example.weatherapp.data.repository

import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.remote.IWeatherApiService
import com.example.weatherapp.domain.repository.IWeatherRepository
import com.example.weatherapp.utils.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Response
import javax.inject.Inject

class RemoteWeatherRepositoryImpl @Inject constructor(
    private val weatherApiService: IWeatherApiService
) : IWeatherRepository {

    override suspend fun getWeekWeatherForecast(lat: Double, lng: Double): Response<WeatherModel> {
        return weatherApiService.getWeekWeatherForecast(latLng = "$lat,$lng")
    }
}