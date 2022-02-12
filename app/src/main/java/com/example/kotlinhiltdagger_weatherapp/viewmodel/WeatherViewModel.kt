package com.example.kotlinhiltdagger_weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinhiltdagger_weatherapp.model.Weather
import com.example.kotlinhiltdagger_weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :ViewModel(){

   private val _resp=MutableLiveData<Weather>()
   val weatherResp:LiveData<Weather>
    get() = _resp

    //state flow
    private val weatherStateFlow= MutableStateFlow(Weather())
    val stateFlow: MutableStateFlow<Weather> get() = weatherStateFlow

    init {
       // getWeather()
        viewModelScope.launch (Dispatchers.IO){
            weatherRepository.getWeatherFlowData().collect {
                weatherStateFlow.value=it
            }
        }
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