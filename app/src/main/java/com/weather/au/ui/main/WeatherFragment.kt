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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_weatherlist.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(context)
        }
        activity?.let {
            weatherViewModel = ViewModelProviders.of(it).get(WeatherViewModel::class.java)

            weatherViewModel.setSortType(arguments?.getString(SORT_TYPE) ?: "" )

            weatherViewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
                loading.visibility = if (isLoading) View.VISIBLE else View.GONE
                if (isLoading) {
                    loadError.visibility = View.GONE
                }
            })
            weatherViewModel.loadError.observe(viewLifecycleOwner, Observer { isError ->
                loadError.visibility = if (isError) View.VISIBLE else View.GONE
            })
            weatherViewModel.weatherDataList.observe(viewLifecycleOwner, Observer { weatherData ->
                weatherAdapter.updateWeatherList(weatherData)
            })

            refreshLayout.setOnRefreshListener {
                loadError.visibility = View.GONE
                weatherViewModel.refresh()
                refreshLayout.isRefreshing = false
            }
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        private const val SORT_TYPE = "sort_type"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int, sortBy: String): WeatherFragment {
            var sort = sortBy
            return WeatherFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putString(SORT_TYPE, sortBy)
                }
            }
        }
    }
}