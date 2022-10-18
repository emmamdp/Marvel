package com.emdp.domain.usecase

import arrow.core.Either
import com.emdp.domain.DomainContract
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.domain.FailureBo

class GetCharactersUc(
    private val dataRepository: DomainContract.Data.DataRepository<CharactersBo>
) : DomainContract.Presentation.UseCase<Any, CharactersBo> {

    override suspend fun run(params: Any?): Either<FailureBo, CharactersBo> =
        dataRepository.getCharacters(params as Boolean)
}