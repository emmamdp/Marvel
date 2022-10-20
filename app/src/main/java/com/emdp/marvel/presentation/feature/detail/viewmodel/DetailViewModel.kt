package com.emdp.marvel.presentation.feature.detail.viewmodel

import androidx.lifecycle.viewModelScope
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.domain.FailureBo
import com.emdp.domain.feature.CharactersDomainBridge
import com.emdp.marvel.presentation.base.BaseMvvmViewModel
import com.emdp.marvel.presentation.base.ScreenState
import com.emdp.marvel.presentation.domain.boToVo
import com.emdp.marvel.presentation.feature.detail.view.state.DetailState

class DetailViewModel(
    bridge: CharactersDomainBridge<CharactersBo>
) : BaseMvvmViewModel<CharactersDomainBridge<CharactersBo>, DetailState>(bridge = bridge) {

    fun onViewCreated(characterId: Int) {
        _screenState.value = ScreenState.Loading
        bridge.getCharacterDetail(
            scope = viewModelScope,
            id = characterId,
            onResult = { it.fold(::handleError, ::handleSuccess) }
        )
    }

    private fun handleSuccess(character: CharactersBo) {
        _screenState.value =
            ScreenState.Render(
                DetailState.ShowCharacterDetail(character = character.data.results[0].boToVo())
            )
    }

    private fun handleError(failureBo: FailureBo) {
        _screenState.value =
            ScreenState.Render(DetailState.ShowError(failure = failureBo.boToVo()))
    }
}