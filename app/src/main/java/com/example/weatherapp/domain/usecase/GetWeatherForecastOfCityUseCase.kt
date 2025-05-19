package com.example.weatherapp.domain.usecase

import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.domain.repository.IWeatherRepository
import com.example.weatherapp.utils.ApiResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherForecastOfCityUseCase @Inject constructor(
    private val repository: IWeatherRepository
) {
    operator fun invoke(lat: Double, lng: Double) = flow<ApiResult<WeatherModel>> {
        emit(ApiResult.Loading())
        val response = repository.getWeekWeatherForecast(lat, lng)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(ApiResult.Success(body))
            } else {
                emit(ApiResult.Error(Exception("Response body is null")))
            }
        } else {
            emit(ApiResult.Error(Exception(response.errorBody()?.toString())))
        }
    }
}