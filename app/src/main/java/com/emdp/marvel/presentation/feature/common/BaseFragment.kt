package com.emdp.marvel.presentation.feature.common

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.emdp.marvel.presentation.feature.main.view.ui.MainActivity
import com.emdp.marvel.presentation.utils.showSnackbar
import com.google.android.material.snackbar.Snackbar

open class BaseFragment : Fragment() {

    private lateinit var parentActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = activity as MainActivity
    }

    fun showBackArrow() {
        parentActivity.showHideBackArrow(show = true)
    }

    fun hideBackArrow() {
        parentActivity.showHideBackArrow(show = false)
    }

    fun showProgressBar() {
        parentActivity.showHideProgressBar(show = true)
    }

    fun hideProgressBar() {
        parentActivity.showHideProgressBar(show = false)
    }

    fun showSnackbarError(textError: String) {
        requireView().showSnackbar(
            text = textError,
            textAction = "Error",
            backgroundColor = Color.RED,
            textColor = Color.WHITE
        )
    }

    fun isOnBackPressed(): Boolean = parentActivity.getOnBackPressedState()

    fun resetOnBackPressed() {
        parentActivity.resetOnBackPressedState()
    }
}