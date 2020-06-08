package com.weather.au.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.weather.au.R
import com.weather.au.model.WeatherData
import kotlinx.android.synthetic.main.activity_weather_detail.*

class WeatherDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)

        //setSupportActionBar(findViewById(R.id.toolbar))

        val weatherData = intent.getParcelableExtra<WeatherData>("weather_details")

        val degreesymbol = '\u00B0'

        tv_suburb_name.text = weatherData.suburb
        tv_weather_type.text = weatherData._weatherCondition
        tv_degree_temperature.text = (weatherData._weatherTemp)+degreesymbol
    }

    fun goBack(view: View) {
        onBackPressed()
    }

}
