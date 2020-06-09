package com.weather.au.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weather.au.R
import com.weather.au.model.Country
import kotlinx.android.synthetic.main.country_list_item.view.*

class CountryInfoAdapter(private val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryInfoAdapter.CountryInfoViewHolder>() {

    fun updateWeatherList(newWeatherList: List<Country>) {
        countryList.clear()
        countryList.addAll(newWeatherList)
        countryList.removeIf {
            it._name.isNullOrEmpty()
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.country_list_item, parent, false)
        return CountryInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryInfoViewHolder, position: Int) {
        holder.itemView.tv_country_name.text = countryList[position]._name
    }

    class CountryInfoViewHolder(view: View): RecyclerView.ViewHolder(view){}
}