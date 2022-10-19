package com.emdp.marvel.presentation.feature.splash.view.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
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
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}