package com.emdp.data.repository

import android.app.Application
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.emdp.data.datasource.CharactersDataSource
import com.emdp.data.datasource.PaginationDataSource
import com.emdp.data.di.dataModule
import com.emdp.domain.DomainContract
import com.emdp.domain.domain.*
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class RepositoryTest : KoinTest {
    private val repository: DomainContract.Data.DataRepository<CharactersBo> by inject()
    private lateinit var mockCharactersDataSource: CharactersDataSource
    private lateinit var mockPaginationDataSource: PaginationDataSource
    private lateinit var mockContext: Application

    @Before
    fun setUp() {
        mockCharactersDataSource = mock()
        mockPaginationDataSource = mock()
        mockContext = mock()

        startKoin {
            androidContext(mockContext)
            modules(listOf(dataModule, module {
                factory { mockCharactersDataSource }
                factory { mockPaginationDataSource }
            }))
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `check that if data-source response connection fails, an error is returned`() =
        runBlocking {
            // Given
            whenever(mockCharactersDataSource.getCharactersResponse(0))
                .doReturn(FailureBo.NoConnection.left())
            // When
            val actualResutl = repository.getCharacters(false)
            // Then
            Assert.assertTrue((actualResutl as? Either.Left<FailureBo>)?.value is FailureBo.NoConnection)
        }

    @Test
    fun `check that if data-source response no data, an error is returned`() =
        runBlocking {
            // Given
            whenever(mockCharactersDataSource.getCharactersResponse(0))
                .doReturn(FailureBo.NoData.left())
            // When
            val actualResutl = repository.getCharacters(false)
            // Then
            Assert.assertTrue((actualResutl as? Either.Left<FailureBo>)?.value is FailureBo.NoData)
        }

    @Test
    fun `check that if data-source response a server error, an error is returned`() =
        runBlocking {
            // Given
            whenever(mockCharactersDataSource.getCharactersResponse(0))
                .doReturn(FailureBo.ServerError("Not found").left())
            // When
            val actualResutl = repository.getCharacters(false)
            // Then
            Assert.assertTrue((actualResutl as? Either.Left<FailureBo>)?.value is FailureBo.ServerError)
        }

    @Test
    fun `check tha if data-source response is successful, a List of CharactersBo is returned`() =
        runBlocking {
            // Given
            whenever(mockCharactersDataSource.getCharactersResponse(0))
                .doReturn(getDummyCharactersBo().right())
            // When
            val actualResutl = mockCharactersDataSource.getCharactersResponse(0)
            // Then
            Assert.assertTrue((actualResutl as? Either.Right<CharactersBo>) != null)
        }

    private fun getDummyCharactersBo() = CharactersBo(
        attributionHTML = "<a href=\"http://marvel.com\">Data provided by Marvel. © 2022 MARVEL</a>",
        attributionText = "Data provided by Marvel. © 2022 MARVEL",
        code = 200,
        copyright = "© 2022 MARVEL",
        `data` = DataBo(
            count = 20,
            limit = 20,
            offset = 0,
            results = listOf(
                ResultBo(
                    comics = ComicsBo(
                        available = 0,
                        collectionURI = "",
                        items = listOf(),
                        returned = 0
                    ),
                    description = "",
                    events = EventsBo(
                        available = 0,
                        collectionURI = "",
                        items = listOf(),
                        returned = 0
                    ),
                    id = 1011334,
                    modified = "2014-04-29T14:18:17-0400",
                    name = "3-D Man",
                    resourceURI = "http://gateway.marvel.com/v1/public/characters/1011334",
                    series = SeriesBo(
                        available = 0,
                        collectionURI = "",
                        items = listOf(),
                        returned = 0
                    ),
                    stories = StoriesBo(
                        available = 0,
                        collectionURI = "",
                        items = listOf(),
                        returned = 0
                    ),
                    thumbnail = ThumbnailBo(
                        extension = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                        path = "jpg"
                    ),
                    urls = listOf()
                )
            ),
            total = 1562
        ),
        etag = "6a2801b21b1ba5e4401d2299d9869f8bd4052a2f",
        status = "Ok"
    )
}