package com.emdp.marvel.presentation.di

import com.emdp.domain.base.BaseDomainBridge
import com.emdp.marvel.presentation.feature.characters.viewmodel.CharactersViewModel
import com.emdp.marvel.presentation.feature.splash.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { SplashViewModel(bridge = BaseDomainBridge.None) }
    viewModel { CharactersViewModel(bridge = get()) }
}