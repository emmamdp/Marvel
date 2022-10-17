package com.emdp.marvel.presentation.base

sealed class ScreenState<out T : BaseState> {
    object Idle : ScreenState<Nothing>()
    object Loading : ScreenState<Nothing>()
    class Render<out T : BaseState>(val renderState: T) : ScreenState<T>()
}
