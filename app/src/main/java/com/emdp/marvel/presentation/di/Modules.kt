package com.emdp.marvel.presentation.di

import com.emdp.marvel.presentation.feature.characters.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CharactersViewModel(bridge = get()) }
}