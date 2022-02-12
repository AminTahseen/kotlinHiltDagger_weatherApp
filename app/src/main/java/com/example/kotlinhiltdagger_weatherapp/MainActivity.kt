package com.example.kotlinhiltdagger_weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinhiltdagger_weatherapp.api.ApiServiceInterface
import com.example.kotlinhiltdagger_weatherapp.databinding.ActivityMainBinding
import com.example.kotlinhiltdagger_weatherapp.di.AppModule
import com.example.kotlinhiltdagger_weatherapp.repository.WeatherRepository
import com.example.kotlinhiltdagger_weatherapp.viewmodel.WeatherViewModel
import com.example.kotlinhiltdagger_weatherapp.viewmodel.WeatherViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var  apiServiceInterface: ApiServiceInterface
    private lateinit var weatherRepository:WeatherRepository
    private lateinit var viewModel:WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linkXML()
        initUI()
        GlobalScope.launch {
            viewModel.stateFlow.collect {
                binding.apply {
                tvTemperature.text=it.temperature
                tvDescription.text=it.description
                tvWind.text=it.wind
                val forecast = it.forecast
                tvForecast1.text = "${forecast?.get(0)?.temperature}/ ${forecast?.get(0)?.wind}"
                tvForecast2.text = "${forecast?.get(1)?.temperature}/ ${forecast?.get(1)?.wind}"
                tvForecast3.text = "${forecast?.get(2)?.temperature}/ ${forecast?.get(2)?.wind}"
                }
            }
        }
    }

    private fun initUI(){
        apiServiceInterface=AppModule.provideRetrofitInstance(AppModule.provideBaseURL())
        weatherRepository= WeatherRepository(apiServiceInterface)
        viewModel= ViewModelProvider(this, WeatherViewModelFactory(weatherRepository))[WeatherViewModel::class.java]
    }
    private fun linkXML(){
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}