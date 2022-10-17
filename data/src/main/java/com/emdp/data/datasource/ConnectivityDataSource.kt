package com.emdp.data.datasource

interface ConnectivityDataSource {

    companion object {
        const val CONNECTIVITY_DATA_SOURCE_TAG = "connectivityDataSource"
    }
    fun checkNetworkConnectionAvailability(): Boolean
}