package com.weather.au.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.weather.au.R

private val TAB_TITLES = arrayOf(
    R.string.atoz,
    R.string.temperature,
    R.string.last_updated
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a weatherFragment (defined as a static inner class below).
        return when(position) {
            1 -> WeatherFragment.newInstance(position + 1, "temperature")
            2 -> WeatherFragment.newInstance(position + 1, "last_updated")
            else -> WeatherFragment.newInstance(position + 1, "aToz")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}