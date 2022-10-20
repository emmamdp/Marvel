package com.emdp.marvel.presentation.feature.characters.view.state

import com.emdp.marvel.presentation.base.BaseState
import com.emdp.marvel.presentation.domain.FailureVo
import com.emdp.marvel.presentation.domain.CharacterVo

sealed class CharactersState : BaseState() {
    class ShowCharactersList(val charactersList: List<CharacterVo>) : CharactersState()
    class ShowError(val failure: FailureVo) : CharactersState()
}
