package com.weather.au.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weather.au.R
import com.weather.au.model.WeatherData
import kotlinx.android.synthetic.main.weather_list_item.view.*

class WeatherListAdapter(private val weatherList: ArrayList<WeatherData>): RecyclerView.Adapter<WeatherListAdapter.WeatherListViewHolder>() {

    fun updateWeatherList(newWeatherList: List<WeatherData>) {
        weatherList.clear()
        weatherList.addAll(newWeatherList)
        weatherList.removeIf {
            it._weatherTemp.isNullOrEmpty()
        }
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
        val degreesymbol = '\u00B0'
        holder.view.tv_suburb.text = weatherList[position].suburb
        holder.view.tv_weather.text = weatherList[position]._weatherCondition
        holder.view.tv_temperature.text = (weatherList[position]._weatherTemp)+degreesymbol
        holder.view.weather_list_layout.setOnClickListener {
            val intent = Intent(it.context, WeatherDetailActivity::class.java)
                .putExtra("weather_details", weatherList[position])
            it.context.startActivity(intent)
        }
    }

    class WeatherListViewHolder(var view: View): RecyclerView.ViewHolder(view)
}