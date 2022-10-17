package com.emdp.data.datasource

import arrow.core.Either
import com.emdp.data.domain.dtoToBo
import com.emdp.data.service.MarvelApiService
import com.emdp.data.utils.retrofitSafeCall
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.domain.FailureBo
import retrofit2.Retrofit

class CharactersDataSourceImpl(
    private val retrofit: Retrofit
) : CharactersDataSource {

    override suspend fun fetchCharactersResponse(): Either<FailureBo, CharactersBo> =
        retrofitSafeCall(
            retrofitRequest = retrofit.create(MarvelApiService::class.java)::getCharactersAsync,
            transform = { it.dtoToBo() }
        )
}