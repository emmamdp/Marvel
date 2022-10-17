package com.emdp.data.utils

import com.emdp.data.datasource.ConnectivityDataSource
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class ConnectivityInterceptor(
    private val connectivityInterceptor: ConnectivityDataSource
) : Interceptor {

    companion object {
        const val CONNECTIVITY_INTERCEPTOR_TAG = "connectivityInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!connectivityInterceptor.checkNetworkConnectionAvailability()) {
            throw NoConnectivityException()
        }
        return chain.proceed(chain.request())
    }
}

class NoConnectivityException : IOException()