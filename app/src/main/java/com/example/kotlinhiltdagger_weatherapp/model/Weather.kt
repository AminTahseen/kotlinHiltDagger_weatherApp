package com.example.kotlinhiltdagger_weatherapp.model

data class Weather(
    val description: String?=null,
    val forecast: List<Forecast>?=null,
    val temperature: String?=null,
    val wind: String?=null
)