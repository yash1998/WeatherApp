package com.example.weatherapp.presentation.models

import androidx.annotation.DrawableRes
import com.example.weatherapp.R

enum class CardTypes(@DrawableRes val drawable: Int) {
    MAIN(R.drawable.ic_rainy), DAY_WEATHER(R.drawable.ic_rainy),
    WINDSPEED(R.drawable.ic_windspeed), HUMIDITY(R.drawable.ic_humidity),
    UV_INDEX(R.drawable.ic_humidity), SUNRISE(R.drawable.ic_sunrise)
}
