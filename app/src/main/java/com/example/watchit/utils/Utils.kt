package com.example.watchit.utils

import android.app.Activity
import android.content.res.Configuration
import android.util.DisplayMetrics

object Utils {

     fun getColumnsNumber(activity: Activity): Int {
        val displayMetrics = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        return when (activity.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            width > 1000 -> {
                2
            }

            width > 1700 -> {
                4
            }

            width > 1200 -> {
                3
            }
            else -> {
                3
            }


        }
    }
}