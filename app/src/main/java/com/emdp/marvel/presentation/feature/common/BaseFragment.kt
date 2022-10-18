package com.emdp.marvel.presentation.feature.common

import android.content.Context
import androidx.fragment.app.Fragment
import com.emdp.marvel.presentation.feature.main.view.ui.MainActivity

open class BaseFragment: Fragment() {

    private lateinit var parentActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = activity as MainActivity
    }

    fun showHideBackArrow(show: Boolean) {
        parentActivity.showHideBackArrow(show)
    }

    fun showHideProgressBar(show: Boolean) {
        parentActivity.showHideProgressBar(show)
    }
}