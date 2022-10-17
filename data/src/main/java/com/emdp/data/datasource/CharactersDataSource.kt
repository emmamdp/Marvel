package com.emdp.data.datasource

import arrow.core.Either
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.domain.FailureBo

interface CharactersDataSource {
    suspend fun fetchCharactersResponse(): Either<FailureBo, CharactersBo>
}