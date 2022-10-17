package com.emdp.data.datasource

import android.content.Context
import com.emdp.data.utils.isNetworkAvailable

class AndroidDataSource(
    private val context: Context
) : ConnectivityDataSource {

    override fun checkNetworkConnectionAvailability(): Boolean = context.isNetworkAvailable()
}