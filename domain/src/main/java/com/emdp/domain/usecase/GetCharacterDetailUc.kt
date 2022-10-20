package com.emdp.domain.usecase

import arrow.core.Either
import com.emdp.domain.DomainContract
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.domain.FailureBo

class GetCharacterDetailUc(
    private val dataRepository: DomainContract.Data.DataRepository<CharactersBo>
) : DomainContract.Presentation.UseCase<Any, CharactersBo> {

    companion object {
        const val CHARACTER_DETAIL_TAG = "characterDetailUc"
    }

    override suspend fun run(params: Any?): Either<FailureBo, CharactersBo> =
        dataRepository.getCharacterDetail(params as Int)
}