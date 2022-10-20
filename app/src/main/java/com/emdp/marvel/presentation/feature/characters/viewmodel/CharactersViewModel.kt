package com.emdp.marvel.presentation.feature.characters.viewmodel

import androidx.lifecycle.viewModelScope
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.domain.FailureBo
import com.emdp.domain.feature.CharactersDomainBridge
import com.emdp.marvel.presentation.base.BaseMvvmViewModel
import com.emdp.marvel.presentation.base.ScreenState
import com.emdp.marvel.presentation.domain.CharacterVo
import com.emdp.marvel.presentation.domain.boToVo
import com.emdp.marvel.presentation.domain.boToVoResultList
import com.emdp.marvel.presentation.feature.characters.view.state.CharactersState

class CharactersViewModel(
    bridge: CharactersDomainBridge<CharactersBo>
) : BaseMvvmViewModel<CharactersDomainBridge<CharactersBo>, CharactersState>(bridge = bridge) {

    private var charactersList: MutableList<CharacterVo> = mutableListOf()

    fun onViewCreated() {
        loadCharactersData(false)
    }

    fun moreCharacters() {
        _screenState.value = ScreenState.Loading
        loadCharactersData(true)
    }

    private fun loadCharactersData(isMore: Boolean) {
        bridge.getCharacters(
            scope = viewModelScope,
            isMore = isMore,
            onResult = { it.fold(::handleError, ::handleSuccess) }
        )
    }

    private fun handleSuccess(charactersListBo: CharactersBo) {
        charactersList.addAll(charactersListBo.data.results.boToVoResultList())
        _screenState.value =
            ScreenState.Render(
                CharactersState.ShowCharactersList(charactersList = charactersList)
            )
    }

    private fun handleError(failureBo: FailureBo) {
        _screenState.value =
            ScreenState.Render(CharactersState.ShowError(failure = failureBo.boToVo()))
    }
}