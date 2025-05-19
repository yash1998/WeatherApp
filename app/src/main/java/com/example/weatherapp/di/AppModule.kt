package com.example.weatherapp.di

import com.example.weatherapp.data.remote.IWeatherApiService
import com.example.weatherapp.data.repository.RemoteWeatherRepositoryImpl
import com.example.weatherapp.domain.repository.IWeatherRepository
import com.example.weatherapp.utils.BASE_URL
import com.example.weatherapp.utils.MIME_TYPE
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import java.util.Calendar
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun getJsonSerObj(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }

    @Singleton
    @Provides
    fun getRetrofit(jsonSerObj: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonSerObj.asConverterFactory(MIME_TYPE.toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun getWeatherForecastService(retrofit: Retrofit): IWeatherApiService {
        return retrofit.create(IWeatherApiService::class.java)
    }


    @Singleton
    @Provides
    fun bindRemoteWeatherRepository(weatherApiService: IWeatherApiService): IWeatherRepository {
        return RemoteWeatherRepositoryImpl(weatherApiService)
    }

}