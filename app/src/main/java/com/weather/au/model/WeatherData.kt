package com.weather.au.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("_name")
    val suburb: String?,
    val _country: Country?,
    val _weatherCondition: String?,
    val _weatherTemp: String?,
    val _weatherLastUpdated: Long?,
    val _weatherFeelsLike: String?,
    val _weatherHumidity: String?,
    val _weatherWind: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Country::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(suburb)
        parcel.writeParcelable(_country, flags)
        parcel.writeString(_weatherCondition)
        parcel.writeString(_weatherTemp)
        parcel.writeValue(_weatherLastUpdated)
        parcel.writeString(_weatherFeelsLike)
        parcel.writeString(_weatherHumidity)
        parcel.writeString(_weatherWind)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherData> {
        override fun createFromParcel(parcel: Parcel): WeatherData {
            return WeatherData(parcel)
        }

        override fun newArray(size: Int): Array<WeatherData?> {
            return arrayOfNulls(size)
        }
    }
}