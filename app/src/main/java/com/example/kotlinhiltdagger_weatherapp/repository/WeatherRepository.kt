package com.example.kotlinhiltdagger_weatherapp.repository

import com.example.kotlinhiltdagger_weatherapp.api.ApiServiceInterface
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService:ApiServiceInterface){
    suspend fun getWeather()=apiService.getWeather()
}