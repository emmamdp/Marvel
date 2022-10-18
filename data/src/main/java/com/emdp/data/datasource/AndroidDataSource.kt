package com.emdp.data.datasource

import android.content.Context
import com.emdp.data.utils.isNetworkAvailable

class AndroidDataSource(
    private val context: Context
) : ConnectivityDataSource, PaginationDataSource {

    override fun checkNetworkConnectionAvailability(): Boolean = context.isNetworkAvailable()

    override fun setPagination(pages: Int) {
        context.getSharedPreferences(
            PaginationDataSource.PAGINATION_PREFERENCES,
            Context.MODE_PRIVATE
        )
            .edit()
            .putInt(PaginationDataSource.PAGINATION_PAGES_TAB, pages)
            .apply()
    }

    override fun getPagination(): Int =
        context.getSharedPreferences(
            PaginationDataSource.PAGINATION_PREFERENCES,
            Context.MODE_PRIVATE
        )
            .getInt(PaginationDataSource.PAGINATION_PAGES_TAB, 0)
}