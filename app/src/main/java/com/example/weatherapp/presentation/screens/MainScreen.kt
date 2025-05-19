package com.example.weatherapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.presentation.models.BriefWeather
import com.example.weatherapp.presentation.models.DayWiseBriefWeather
import com.example.weatherapp.presentation.viewmodel.MainScreenVM
import com.example.weatherapp.utils.getBriefWeatherInfo
import com.example.weatherapp.utils.getListOfWeekForecast
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreenCombinedView(mainScreenVM: MainScreenVM) {
    val state = mainScreenVM.weatherForecastFlow.collectAsState()
    val permissionState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    val context = LocalContext.current
    val isLocationFetched = remember { mutableStateOf<Boolean>(false) }
    LaunchedEffect(Unit) {
        if (!permissionState.allPermissionsGranted) {
            permissionState.launchMultiplePermissionRequest()
        }
    }
    LaunchedEffect(permissionState.allPermissionsGranted) {
        if (permissionState.allPermissionsGranted && !isLocationFetched.value) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    isLocationFetched.value = true
                    mainScreenVM.getWeatherForecast(it.latitude, it.longitude)
                }
            }
        }
    }
    when (state.value) {
        is MainScreenState.Loading -> CircularProgressIndicator()

        is MainScreenState.Success -> {
            MainScreenView((state.value as MainScreenState.Success).data)
        }

        is MainScreenState.Error -> Text(
            text = "error"
        )
    }
}

@Composable
fun MainScreenView(data: WeatherModel) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Navigation,
                contentDescription = null,
                modifier = Modifier
                    .rotate(-90f)
                    .alpha(0.5f)
            )
            CityWeatherBigCard(data.getBriefWeatherInfo())
            Icon(
                imageVector = Icons.Default.Navigation,
                contentDescription = null,
                modifier = Modifier
                    .rotate(90f)
                    .alpha(0.5f)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        DailyWeatherCardsHorizontalView(data.getListOfWeekForecast())
    }
}

@Composable
fun CityWeatherBigCard(briefWeather: BriefWeather) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(vertical = 20.dp)
        ) {
            Image(
                painter = painterResource(briefWeather.weatherIcon),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(20.dp)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 2.dp)
            ) {
                Text(
                    text = briefWeather.cityName,
                    style = MaterialTheme.typography.headlineMedium.copy(fontFamily = FontFamily.Serif),
                    modifier = Modifier.padding(horizontal = 7.dp)
                )
                Icon(
                    imageVector = Icons.Default.Navigation,
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterVertically)
                        .offset(y = (-3).dp)
                        .rotate(45f)
                )
            }
            Text(
                text = stringResource(R.string.temp_in_celsius, briefWeather.avgTemp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(2.dp),
                style = MaterialTheme.typography.displayMedium.copy(fontFamily = FontFamily.Serif)
            )
            Text(
                text = briefWeather.briefWeatherDesc,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(2.dp),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = FontFamily.Serif, fontStyle = FontStyle.Italic
                )
            )
        }
    }
}

@Composable
fun DailyWeatherCardsHorizontalView(data: List<DayWiseBriefWeather>) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(items = data) {
            DailyWeatherCard(it)
        }
    }
}

@Composable
fun DailyWeatherCard(dayWiseBriefWeather: DayWiseBriefWeather) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .wrapContentWidth()
            .width(130.dp)
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(dayWiseBriefWeather.weatherIcon),
                contentDescription = null,
                modifier = Modifier
                    .width(50.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp)
            )
            Text(
                text = stringResource(R.string.temp_in_celsius, dayWiseBriefWeather.avgTemp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp),
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily.Serif)
            )

            Text(
                text = dayWiseBriefWeather.dayName.lowercase().capitalize(),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(2.dp),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic
                )
            )
        }
    }
}