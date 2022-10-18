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

    companion object {
        private const val LIMIT = 20
    }

    override suspend fun getCharactersResponse(offset: Int): Either<FailureBo, CharactersBo> =
        retrofitSafeCall(
            retrofitRequest = retrofit.create(MarvelApiService::class.java)::getCharactersAsync,
            ts = 1,
            apkikey = "1f0d1a0d30b1cd928fa38de270db8eec",
            hash = "719dc4eb95eb4981b37926843ceda07d",
            offset = offset,
            limit = LIMIT,
            transform = { it.dtoToBo() }
        )
}