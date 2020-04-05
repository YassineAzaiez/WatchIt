package com.example.core.utils

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission

@RequiresPermission(ACCESS_NETWORK_STATE)
fun Context.isNetworkAvailabe() : Boolean{
    val connectivityManager = this.getSystemService(CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        val networkInfo = it.activeNetworkInfo
        networkInfo?.let{ netInfo ->
            if(netInfo.isConnected) return true
        }
    }
    return false
}