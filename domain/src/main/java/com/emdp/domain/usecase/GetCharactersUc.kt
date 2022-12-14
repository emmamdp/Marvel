package com.emdp.domain.usecase

import arrow.core.Either
import com.emdp.domain.DomainContract
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.domain.FailureBo

class GetCharactersUc(
    private val dataRepository: DomainContract.Data.DataRepository<CharactersBo>
) : DomainContract.Presentation.UseCase<Any, CharactersBo> {

    companion object {
        const val CHARACTERS_LIST_TAG = "charactersUc"
    }

    override suspend fun run(params: Any?): Either<FailureBo, CharactersBo> =
        dataRepository.getCharacters(params as Boolean)
}