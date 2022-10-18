package com.emdp.data.datasource

interface PaginationDataSource {

    companion object {
        const val PAGINATION_PREFERENCES = "paginationPreferences"
        const val PAGINATION_PAGES_TAB = "paginationPages"
    }

    fun setPagination(pages: Int)
    fun getPagination(): Int
}