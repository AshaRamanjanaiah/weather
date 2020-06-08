package com.weather.au.model

import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("_name")
    val suburb: String?,
    val _country: Country,
    val _weatherCondition: String?,
    val _weatherTemp: String?,
    val _weatherLastUpdated: Long) {
}