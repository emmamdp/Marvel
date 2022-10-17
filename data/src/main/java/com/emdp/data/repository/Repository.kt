package com.emdp.data.repository

import arrow.core.Either
import com.emdp.data.datasource.CharactersDataSource
import com.emdp.domain.DomainContract
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.domain.FailureBo

object Repository : DomainContract.Data.DataRepository<CharactersBo> {

    lateinit var charactersDataSource: CharactersDataSource

    override suspend fun fetchCharacters(): Either<FailureBo, CharactersBo> =
        charactersDataSource.fetchCharactersResponse()

}