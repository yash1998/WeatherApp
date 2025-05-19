package com.example.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecase.GetWeatherForecastOfCityUseCase
import com.example.weatherapp.presentation.screens.MainScreenState
import com.example.weatherapp.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainScreenVM @Inject constructor(
    val getWeatherForecastOfCityUseCase: GetWeatherForecastOfCityUseCase
) : ViewModel() {

    private var _weatherForecastFlow = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val weatherForecastFlow: StateFlow<MainScreenState>
        get() = _weatherForecastFlow


    fun getWeatherForecast(lat: Double, lng: Double) = viewModelScope.launch(Dispatchers.IO) {
        _weatherForecastFlow.emit(MainScreenState.Loading)
        getWeatherForecastOfCityUseCase.invoke(lat, lng).collectLatest {
            when (it) {
                is ApiResult.Success -> {
                    _weatherForecastFlow.emit(MainScreenState.Success(it.data))
                }

                is ApiResult.Error -> {
                    _weatherForecastFlow.emit(MainScreenState.Error(it.error))
                }

                is ApiResult.Loading -> {
                    _weatherForecastFlow.emit(MainScreenState.Loading)
                }
            }
        }
    }
}