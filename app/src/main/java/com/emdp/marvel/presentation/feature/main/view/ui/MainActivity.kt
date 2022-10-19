package com.emdp.marvel.presentation.feature.main.view.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.emdp.marvel.databinding.ActivityMainBinding
import com.emdp.marvel.presentation.utils.isShow
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var isOnBackPressed = false
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListeners()
    }

    fun showHideBackArrow(show: Boolean) {
        binding.ivBackArrow.isShow(show)
    }

    fun showHideProgressBar(show: Boolean) {
        binding.progressBar.root.isShow(show)
    }

    fun getOnBackPressedState(): Boolean = isOnBackPressed

    fun resetOnBackPressedState() {
        isOnBackPressed = false
    }

    private fun initListeners() {
        binding.ivBackArrow.setOnClickListener {
            isOnBackPressed = true
            onBackPressedDispatcher.onBackPressed()
        }
    }
}