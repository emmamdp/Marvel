package com.emdp.domain.feature

import arrow.core.Either
import com.emdp.domain.DomainContract
import com.emdp.domain.base.BaseDomainBridge
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.domain.FailureBo
import kotlinx.coroutines.CoroutineScope

interface CharactersDomainBridge<out S> : BaseDomainBridge {
    fun fetchCharacters(scope: CoroutineScope, onResult: (Either<FailureBo, S>) -> Unit = {})
}

internal class CharactersDomainBridgeImpl(
    private val fetchCharactersUc: DomainContract.Presentation.UseCase<Any, CharactersBo>
) : CharactersDomainBridge<CharactersBo> {

    override fun fetchCharacters(
        scope: CoroutineScope,
        onResult: (Either<FailureBo, CharactersBo>) -> Unit
    ) {
        fetchCharactersUc.invoke(scope = scope, onResult = onResult)
    }
}