package com.weather.au.ui.main

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.weather.au.R
import kotlinx.android.synthetic.main.country_info.*
import kotlinx.android.synthetic.main.country_info.view.*

class CountryInfoDialogFragment: DialogFragment() {

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

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }


}