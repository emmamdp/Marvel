package com.emdp.marvel.presentation.feature.characters.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.domain.FailureBo
import com.emdp.domain.feature.CharactersDomainBridge
import com.emdp.marvel.presentation.base.BaseMvvmViewModel
import com.emdp.marvel.presentation.base.ScreenState
import com.emdp.marvel.presentation.domain.boToVo
import com.emdp.marvel.presentation.domain.boToVoResultList
import com.emdp.marvel.presentation.feature.characters.view.state.CharactersState

class CharactersViewModel(
    bridge: CharactersDomainBridge<CharactersBo>
) : BaseMvvmViewModel<CharactersDomainBridge<CharactersBo>, CharactersState>(bridge = bridge) {

    fun onViewCreated() {
        _screenState.value = ScreenState.Loading
        bridge.fetchCharacters(
            scope = viewModelScope,
            onResult = { it.fold(::handleError, ::handleSuccess) }
        )
    }

    private fun handleSuccess(charactersList: CharactersBo) {
        val charactersListVo = charactersList.data.results.boToVoResultList()
        Log.d("characters", charactersListVo.toString())
        _screenState.value =
            ScreenState.Render(
                CharactersState.ShowCharactersList(charactersList = charactersListVo)
            )
    }

    private fun handleError(failureBo: FailureBo) {
        _screenState.value =
            ScreenState.Render(CharactersState.ShowError(failure = failureBo.boToVo()))
    }
}