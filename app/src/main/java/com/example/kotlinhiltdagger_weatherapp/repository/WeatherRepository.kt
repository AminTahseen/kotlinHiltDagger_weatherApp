package com.example.kotlinhiltdagger_weatherapp.repository

import com.example.kotlinhiltdagger_weatherapp.api.ApiServiceInterface
import com.example.kotlinhiltdagger_weatherapp.model.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService:ApiServiceInterface){
    suspend fun getWeather()=apiService.getWeather()

    suspend fun getWeatherFlowData(): Flow<Weather> {
        return flow<Weather>{
            val result=apiService.getWeather()
            result.body()?.let { emit(it) }
        }
    }

}