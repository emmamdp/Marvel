package com.emdp.marvel.presentation.feature.splash.viewmodel

import com.emdp.domain.base.BaseDomainBridge
import com.emdp.marvel.presentation.base.BaseMvvmViewModel
import com.emdp.marvel.presentation.base.ScreenState
import com.emdp.marvel.presentation.feature.splash.view.state.SplashState

class SplashViewModel(bridge: BaseDomainBridge.None) :
    BaseMvvmViewModel<BaseDomainBridge.None, SplashState>(bridge = bridge) {

    fun onViewCreated() {
        _screenState.value = ScreenState.Render(SplashState.LoadingFinished)
    }
}