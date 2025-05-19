package com.example.weatherapp.utils

import android.icu.util.Calendar
import com.example.weatherapp.R
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.presentation.models.BriefWeather
import com.example.weatherapp.presentation.models.DayWiseBriefWeather
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun WeatherModel.getBriefWeatherInfo(): BriefWeather {
    return BriefWeather(
        location.name,
        current.tempC,
        current.condition.text,
        weatherCodeToDrawable[current.condition.code] ?: R.drawable.ic_sunny
    )

}

fun WeatherModel.getListOfWeekForecast(): List<DayWiseBriefWeather> {
    return mutableListOf<DayWiseBriefWeather>().apply {
        forecast.forecastday.forEach {
            add(
                DayWiseBriefWeather(
                    it.dateEpoch.getDayOfWeek(), it.day.avgtempC,
                    weatherCodeToDrawable[it.day.condition.code] ?: R.drawable.ic_sunny
                )
            )
        }
    }
}

fun Long.getDayOfWeek(): String {
    return Instant.fromEpochSeconds(this)
        .toLocalDateTime(TimeZone.currentSystemDefault()).dayOfWeek.name
}