package com.weather.au.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weather.au.R
import com.weather.au.model.Weather
import kotlinx.android.synthetic.main.weather_list_item.view.*

class WeatherListAdapter(private val weatherList: ArrayList<Weather>): RecyclerView.Adapter<WeatherListAdapter.WeatherListViewHolder>() {

    fun updateWeatherList(newWeatherList: List<Weather>) {
        weatherList.clear()
        weatherList.addAll(newWeatherList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.weather_list_item, parent, false)
        return WeatherListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        holder.view.tv_weather_item.text = weatherList[position].day
    }

    class WeatherListViewHolder(var view: View): RecyclerView.ViewHolder(view)
}