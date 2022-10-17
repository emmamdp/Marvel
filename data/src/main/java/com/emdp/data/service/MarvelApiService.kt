package com.emdp.data.service

import com.emdp.data.domain.CharactersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {
    @GET("v1/public/characters")
    suspend fun getCharactersAsync(
        @Query("ts") ts: Int = 1,
        @Query("apikey") apikey: String = "1f0d1a0d30b1cd928fa38de270db8eec",
        @Query("hash") hash: String = "719dc4eb95eb4981b37926843ceda07d"
    ): Response<CharactersDto>
}