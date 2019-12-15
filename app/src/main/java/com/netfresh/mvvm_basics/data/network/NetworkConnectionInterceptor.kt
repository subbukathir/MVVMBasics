package com.netfresh.mvvm_basics.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.netfresh.mvvm_basics.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    val context: Context
): Interceptor {
    val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if(!isInternetAvailable())
            throw NoInternetException("Check internet connection")

        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable():Boolean{
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it!=null && it.isConnected
        }
    }
}