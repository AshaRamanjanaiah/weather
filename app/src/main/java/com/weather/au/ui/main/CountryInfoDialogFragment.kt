package com.weather.au.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.weather.au.R
import com.weather.au.Utils.Utils
import com.weather.au.model.Country
import kotlinx.android.synthetic.main.country_info.*
import kotlinx.android.synthetic.main.country_info.view.*

class CountryInfoDialogFragment: DialogFragment() {

    private val countryInfoAdapter = CountryInfoAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.country_info, container, false)

        view.cancel_dialog.setOnClickListener(View.OnClickListener {
            dismiss()
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_country_list.apply {
            adapter = countryInfoAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val countryList = arguments?.getParcelableArrayList<Country>(Utils.COUNTRY_INFO) ?: arrayListOf()

        countryInfoAdapter.updateWeatherList(countryList)
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }


}