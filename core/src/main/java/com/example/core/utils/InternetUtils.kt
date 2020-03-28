package com.example.core.utils



import android.content.Context
import android.net.ConnectivityManager


object InternetUtils {


    fun Context.isConnectedToNetwork(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }
}