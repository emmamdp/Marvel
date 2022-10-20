package com.emdp.data.datasource

import arrow.core.Either
import com.emdp.data.domain.dtoToBo
import com.emdp.data.service.MarvelApiService
import com.emdp.data.utils.retrofitCharacterSafeCall
import com.emdp.data.utils.retrofitCharactersSafeCall
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.domain.FailureBo
import retrofit2.Retrofit

class CharactersDataSourceImpl(
    private val retrofit: Retrofit
) : CharactersDataSource {

    companion object {
        private const val LIMIT = 20
        private const val TS = 1
        private const val API_KEY = "1f0d1a0d30b1cd928fa38de270db8eec"
        private const val HASH = "719dc4eb95eb4981b37926843ceda07d"
    }

    override suspend fun getCharactersResponse(offset: Int): Either<FailureBo, CharactersBo> =
        retrofitCharactersSafeCall(
            retrofitRequest = retrofit.create(MarvelApiService::class.java)::getCharactersAsync,
            ts = TS,
            apkikey = API_KEY,
            hash = HASH,
            offset = offset,
            limit = LIMIT,
            transform = { it.dtoToBo() }
        )

    override suspend fun getCharacterDetailResponse(id: Int): Either<FailureBo, CharactersBo> =
        retrofitCharacterSafeCall(
            retrofitRequest = retrofit.create(MarvelApiService::class.java)::getCharacterDetailAsync,
            id = id,
            ts = TS,
            apkikey = API_KEY,
            hash = HASH,
            transform = { it.dtoToBo() }
        )
}