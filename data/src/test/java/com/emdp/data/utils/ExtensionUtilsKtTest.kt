package com.emdp.data.utils

import arrow.core.Either
import com.emdp.data.di.dataModule
import com.emdp.data.domain.CharactersDto
import com.emdp.data.domain.dtoToBo
import com.emdp.data.service.MarvelApiService
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.domain.FailureBo
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.Retrofit


class ExtensionUtilsKtTest: KoinTest {

    companion object {
        private const val LIMIT = 20
        private const val TS = 1
        private const val OFFSET = 0
        private const val API_KEY = "1f0d1a0d30b1cd928fa38de270db8eec"
        private const val HASH = "719dc4eb95eb4981b37926843ceda07d"
    }

    private val retrofitClient: Retrofit by inject()

    @Before
    fun setUp() {
        startKoin {
            modules(listOf(dataModule))
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `create retrofit safe call`() = runBlocking {
        val request = retrofitClient.create(MarvelApiService::class.java)::getCharactersAsync
        val transform: (CharactersDto) -> CharactersBo = { it.dtoToBo() }
        /*val actualResult = retrofitSafeCall(
            retrofitRequest = request,
            ts = TS,
            apkikey = API_KEY,
            hash = HASH,
            offset = OFFSET,
            limit = LIMIT,
            transform = transform
        )*/
    }
}