package com.emdp.data.service

import com.emdp.data.domain.CharactersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {
    @GET("v1/public/characters")
    suspend fun getCharactersAsync(
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("orderBy") orderBy: String = "name"
    ): Response<CharactersDto>

    @GET("v1/public/characters/{id}")
    suspend fun getCharacterDetailAsync(
        @Path(value = "id", encoded = true) id: Int,
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Response<CharactersDto>
}