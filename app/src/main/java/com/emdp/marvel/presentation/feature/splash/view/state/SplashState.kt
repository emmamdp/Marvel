package com.emdp.marvel.presentation.feature.splash.view.state

import com.emdp.marvel.presentation.base.BaseState

sealed class SplashState : BaseState() {
    object LoadingFinished: SplashState()
}
