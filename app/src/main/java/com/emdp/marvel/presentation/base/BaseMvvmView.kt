package com.emdp.marvel.presentation.base

import com.emdp.domain.base.BaseDomainBridge

interface BaseMvvmView<T : BaseMvvmViewModel<S, U>, S : BaseDomainBridge, U : BaseState> {
    val viewModel: T
    fun processRenderState(renderState: U)
    fun initModel()
}