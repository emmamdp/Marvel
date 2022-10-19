package com.emdp.marvel.presentation.base

import androidx.lifecycle.ViewModel
import com.emdp.domain.base.BaseDomainBridge
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseMvvmViewModel<T : BaseDomainBridge, S : BaseState>(protected val bridge: T) :
    ViewModel() {

    protected val _screenState: MutableStateFlow<ScreenState<S>> by lazy {
        MutableStateFlow(ScreenState.Idle)
    }
    val screenState: StateFlow<ScreenState<S>>
        get() {
            return _screenState
        }
}