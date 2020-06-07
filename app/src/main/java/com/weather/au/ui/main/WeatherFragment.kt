package com.weather.au.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weather.au.R
import com.weather.au.model.Weather
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A Weather fragment containing a weather info.
 */
class WeatherFragment : Fragment() {

    private lateinit var weatherViewModel: WeatherViewModel
    private val weatherAdapter = WeatherListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val textView: TextView = root.findViewById(R.id.section_label)
        weatherViewModel.text.observe(this, Observer<String> {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_weatherlist.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(context)
        }

        var weatherList = arrayListOf(Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy"),
            Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy"),
            Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy"),Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy"),
            Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy"),Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy"),
            Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy"),
            Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy"),Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy"),
            Weather(false, "cloudy"), Weather(true, "rainy"),Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy"),
            Weather(false, "cloudy"), Weather(true, "rainy"),Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy"),
            Weather(false, "cloudy"), Weather(true, "rainy"),Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy"),
            Weather(false, "cloudy"), Weather(true, "rainy"),Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy"),
            Weather(false, "cloudy"), Weather(true, "rainy"),Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy"),
            Weather(false, "cloudy"), Weather(true, "rainy"),Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy"),
            Weather(false, "cloudy"), Weather(true, "rainy"),Weather(true, "Sunny"),
            Weather(false, "cloudy"), Weather(true, "rainy")
        )

        weatherAdapter.updateWeatherList(weatherList)
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): WeatherFragment {
            return WeatherFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}