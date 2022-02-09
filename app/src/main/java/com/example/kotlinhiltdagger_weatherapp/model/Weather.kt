package com.example.kotlinhiltdagger_weatherapp.model

data class Weather(
    val description: String,
    val forecast: List<Forecast>,
    val temperature: String,
    val wind: String
)