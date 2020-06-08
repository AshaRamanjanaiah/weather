package com.weather.au.ui.main

import android.graphics.Typeface
import android.os.Bundle
import android.transition.Slide
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.weather.au.R
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(selectedTab: TabLayout.Tab) {
                viewPager.setCurrentItem(selectedTab.getPosition())
                /*val tabLayout =
                    (tabs.getChildAt(0) as ViewGroup).getChildAt(selectedTab.position) as LinearLayout
                val tabTextView = tabLayout.getChildAt(1) as TextView
                tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.BOLD)*/
            }

            override fun onTabUnselected(unSelectedTab: TabLayout.Tab) {
                /*val tabLayout =
                    (tabs.getChildAt(0) as ViewGroup).getChildAt(unSelectedTab.position) as LinearLayout
                val tabTextView = tabLayout.getChildAt(1) as TextView
                tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.NORMAL)*/
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
    }

    fun showDialog(view: View) {
        var isLargeLayout = false
        val fragmentManager = supportFragmentManager
        val newFragment = CountryInfoDialogFragment()
        if (isLargeLayout) {
            // The device is using a large layout, so show the fragment as a dialog
            newFragment.show(fragmentManager, "dialog")
        } else {
            // The device is smaller, so show the fragment fullscreen
            val transaction = fragmentManager.beginTransaction()
            newFragment.enterTransition = Slide()
            newFragment.exitTransition = Slide()
            transaction
                .add(android.R.id.content, newFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}