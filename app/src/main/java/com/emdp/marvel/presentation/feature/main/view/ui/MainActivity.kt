package com.emdp.marvel.presentation.feature.main.view.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.emdp.marvel.R
import com.emdp.marvel.databinding.ActivityMainBinding
import com.emdp.marvel.presentation.utils.isShow

class MainActivity : AppCompatActivity() {

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
        binding.progressBar.clProgressBar.isShow(show)
    }

    private fun initListeners() {
        binding.ivBackArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}