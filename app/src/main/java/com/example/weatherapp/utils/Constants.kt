package com.example.weatherapp.utils

import androidx.annotation.DrawableRes
import com.example.weatherapp.R


const val BASE_URL = "https://api.weatherapi.com/v1/"
const val API_KEY = "82ad3af3b4874e0c95550308251905"

const val MIME_TYPE = "application/json"


val weatherCodeToDrawable = mapOf(
    // ☀️ SUNNY
    1000 to R.drawable.ic_sunny,

    // ☁️ CLOUDY TYPES
    1003 to R.drawable.ic_cloudy,
    1006 to R.drawable.ic_cloudy,
    1009 to R.drawable.ic_cloudy,

    // 🌧️ RAINY
    1063 to R.drawable.ic_rainy,
    1072 to R.drawable.ic_rainy,
    1150 to R.drawable.ic_rainy,
    1153 to R.drawable.ic_rainy,
    1168 to R.drawable.ic_rainy,
    1171 to R.drawable.ic_rainy,
    1180 to R.drawable.ic_rainy,
    1183 to R.drawable.ic_rainy,
    1186 to R.drawable.ic_rainy,
    1189 to R.drawable.ic_rainy,
    1192 to R.drawable.ic_rainy,
    1195 to R.drawable.ic_rainy,
    1198 to R.drawable.ic_rainy,
    1201 to R.drawable.ic_rainy,
    1240 to R.drawable.ic_rainy,
    1243 to R.drawable.ic_rainy,
    1246 to R.drawable.ic_rainy,

    // ❄️ SNOW
    1066 to R.drawable.ic_snow,
    1069 to R.drawable.ic_snow,
    1114 to R.drawable.ic_snow,
    1117 to R.drawable.ic_snow,
    1210 to R.drawable.ic_snow,
    1213 to R.drawable.ic_snow,
    1216 to R.drawable.ic_snow,
    1219 to R.drawable.ic_snow,
    1222 to R.drawable.ic_snow,
    1225 to R.drawable.ic_snow,
    1237 to R.drawable.ic_snow,
    1255 to R.drawable.ic_snow,
    1258 to R.drawable.ic_snow,
    1261 to R.drawable.ic_snow,
    1264 to R.drawable.ic_snow,

    // ⛈️ THUNDERSTORM
    1087 to R.drawable.ic_thunderstorm,
    1273 to R.drawable.ic_thunderstorm,
    1276 to R.drawable.ic_thunderstorm,
    1279 to R.drawable.ic_thunderstorm,
    1282 to R.drawable.ic_thunderstorm
)
