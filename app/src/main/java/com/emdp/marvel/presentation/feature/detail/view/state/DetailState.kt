package com.emdp.marvel.presentation.feature.detail.view.state

import com.emdp.marvel.presentation.base.BaseState
import com.emdp.marvel.presentation.domain.CharacterVo
import com.emdp.marvel.presentation.domain.FailureVo

sealed class DetailState : BaseState() {
    class ShowCharacterDetail(val character: CharacterVo) : DetailState()
    class ShowError(val failure: FailureVo) : DetailState()
}
