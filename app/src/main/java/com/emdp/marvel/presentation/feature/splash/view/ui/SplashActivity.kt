package com.emdp.marvel.presentation.feature.splash.view.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.emdp.domain.base.BaseDomainBridge
import com.emdp.marvel.presentation.base.BaseMvvmView
import com.emdp.marvel.presentation.base.ScreenState
import com.emdp.marvel.presentation.feature.main.view.ui.MainActivity
import com.emdp.marvel.presentation.feature.splash.view.state.SplashState
import com.emdp.marvel.presentation.feature.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity :
    AppCompatActivity(),
    BaseMvvmView<SplashViewModel, BaseDomainBridge.None, SplashState> {

    companion object {
        private const val DELAY_MILLIS: Long = 1000
    }

    override val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onViewCreated()
    }

    override fun processRenderState(renderState: SplashState) {
        when (renderState) {
            is SplashState.LoadingFinished -> startMainActivity()
        }
    }

    override fun initModel() {
        lifecycleScope.launch {
            viewModel.screenState.collect { screenState ->
                when (screenState) {
                    is ScreenState.Render<SplashState> -> processRenderState(screenState.renderState)
                    else -> {}
                }
            }
        }
    }

    private fun startMainActivity() {
        // This is a little delay to be able to see the splash in fast mobiles
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },
            DELAY_MILLIS
        )
    }
}