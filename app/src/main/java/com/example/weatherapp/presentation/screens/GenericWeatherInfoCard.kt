package com.example.weatherapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.presentation.models.CardTypes


@Composable
fun RowScope.GenericWeatherInfoCard(type: CardTypes, model: WeatherModel) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.weight(1f)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(type.drawable),
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .width(50.dp)
                    .height(50.dp)
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.wrapContentHeight(Alignment.CenterVertically)
            ) {
                Text(
                    text = when (type) {
                        CardTypes.WINDSPEED -> stringResource(
                            R.string.windspeed_in_kmph,
                            model.current.windKph
                        )

                        CardTypes.SUNRISE -> model.forecast.forecastday.get(0).astro.sunrise
                        CardTypes.HUMIDITY -> model.current.humidity
                        CardTypes.UV_INDEX -> model.current.uv
                        else -> model.current.uv
                    }.toString(),
                    style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FontFamily.Serif)
                )
                Text(
                    text = type.name.lowercase().capitalize(),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily.Serif,
                    ), maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}