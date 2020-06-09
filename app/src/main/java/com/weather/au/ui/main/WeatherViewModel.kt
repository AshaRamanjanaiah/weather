package com.weather.au.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weather.au.Utils.Utils
import com.weather.au.model.Country
import com.weather.au.model.Weather
import com.weather.au.model.WeatherData
import com.weather.au.network.WeatherApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class WeatherViewModel : ViewModel() {

    private val TAG = WeatherViewModel::class.java.simpleName

    private val _weatherDataList by lazy { MutableLiveData<List<WeatherData>>() }

    val weatherDataList: LiveData<List<WeatherData>> get() = _weatherDataList

    private val _loading by lazy { MutableLiveData<Boolean>() }
    val loading: LiveData<Boolean> get() = _loading

    private val _loadError by lazy { MutableLiveData<Boolean>() }
    val loadError: LiveData<Boolean> get() = _loadError

    private val _sortBy by lazy { MutableLiveData<String>() }
    val sortBy: LiveData<String> get() = _sortBy

    private val disposable = CompositeDisposable()

    init {
        refresh()
        _loading.value = true
    }

    fun refresh() {
        getWeatherData()
        _loadError.value = false
    }

    fun setSortType(sortType: String) {
        _sortBy.value = sortType
    }

    /**
     * This method gets weather data from network
     */
    private fun getWeatherData() {
        disposable.add(
        WeatherApi.retrofitService.getWeatherInfo()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<Weather>() {
                override fun onSuccess(weather: Weather) {
                    sortWeatherData(weather.data)
                    _loading.value = false
                    _loadError.value = false
                }

                override fun onError(e: Throwable) {
                    if (!_weatherDataList.value.isNullOrEmpty()) {
                        _loadError.value = false
                    } else {
                        _weatherDataList.value = listOf()
                        _loadError.value = true
                    }
                    _loading.value = false
                    Log.d(TAG, "Error loading data")
                }

            })
        )
    }

    /**
     * This method sorts the list by AtoZ, temperature and last updated
     * @param List of weather data
     */
    private fun sortWeatherData(data: List<WeatherData>) {
        var date = ""
        when(_sortBy.value) {
            Utils.SORT_BY_TEMPERATURE -> {
                _weatherDataList.value = data.sortedBy { it._weatherTemp }
            }
            Utils.SORT_BY_LAST_UPDATED -> {
                _weatherDataList.value = data.sortedBy { it._weatherLastUpdated }
                Log.d(TAG, date)
            }
            else -> _weatherDataList.value = data
        }
    }

    /**
     * This method returns countries list
     * @return Array list of countries
     */

    fun getCountryList(): ArrayList<Country?> {
        var countryList = arrayListOf<Country?>()
        _weatherDataList.value?.forEach { data -> countryList.add(data._country) }

        val newCountryList = countryList.distinct()
        countryList.clear()
        countryList.addAll(newCountryList)
        return countryList
    }
}