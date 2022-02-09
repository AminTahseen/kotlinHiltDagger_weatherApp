package com.example.kotlinhiltdagger_weatherapp.api

import com.example.kotlinhiltdagger_weatherapp.model.Weather
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceInterface {
    @GET("/weather/Curitiba")
    suspend fun getWeather():Response<Weather>
}