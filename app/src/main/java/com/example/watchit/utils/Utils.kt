package com.example.watchit.utils

import android.app.Activity
import android.content.res.Configuration
import android.util.DisplayMetrics
import java.text.SimpleDateFormat
import java.util.*

object Utils {

     fun getColumnsNumber(activity: Activity): Int {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
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
    fun formatTime( timeInMin : Int) : String{
        val formatMin = SimpleDateFormat("mm", Locale.ENGLISH)
        val formatHour = SimpleDateFormat("H:mm", Locale.ENGLISH)

        val date = formatMin.parse(timeInMin.toString())
        return formatHour.format(date as Date)
    }

    fun formatDate(date : String ):String{
        val oldDateFormat = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
        val newDateFormat = SimpleDateFormat("d MMM yyyy", Locale.ENGLISH)

        val date = oldDateFormat.parse(date)
        return  newDateFormat.format(date as Date)
    }
}