package com.example.kotlinhiltdagger_weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinhiltdagger_weatherapp.model.Weather
import com.example.kotlinhiltdagger_weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :ViewModel(){

   private val _resp=MutableLiveData<Weather>()
   val weatherResp:LiveData<Weather>
    get() = _resp

    init {
        getWeather()
    }
    private fun getWeather()=viewModelScope.launch {
        weatherRepository.getWeather().let {
            if(it.isSuccessful)
                _resp.postValue(it.body())
            else
                Log.d("WeatherResponseFailure","${it.message()}")
        }
    }
}