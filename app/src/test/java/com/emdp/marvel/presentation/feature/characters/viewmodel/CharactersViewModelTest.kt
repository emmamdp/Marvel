package com.emdp.marvel.presentation.feature.characters.viewmodel

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.emdp.domain.domain.*
import com.emdp.domain.feature.CharactersDomainBridge
import com.emdp.marvel.presentation.base.ScreenState
import com.emdp.marvel.presentation.di.presentationModule
import com.emdp.marvel.presentation.feature.characters.view.state.CharactersState
import com.nhaarman.mockitokotlin2.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

internal class CharactersViewModelTest : KoinTest {

    private val viewModel: CharactersViewModel by inject()
    private lateinit var mockBridge: CharactersDomainBridge<CharactersBo>

    @Before
    fun setUp() {
        mockBridge = mock()
        startKoin {
            modules(listOf(presentationModule, module { factory { mockBridge } }))
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `check that state is 'ShowCharactersList' when characters are fetched`() {
        // Given
        val captor = argumentCaptor<(Either<FailureBo, CharactersBo>) -> Unit>()
        // When
        viewModel.onViewCreated()
        // Then
        verify(mockBridge).getCharacters(
            scope = any(),
            isMore = eq(false),
            onResult = captor.capture()
        )
        verifyNoMoreInteractions(mockBridge)
        captor.firstValue.invoke(getDummyCharactersBo().right())

        Assert.assertTrue(getRenderState() is CharactersState.ShowCharactersList)
    }

    @Test
    fun `check that state is 'ShowError' when characters cannot be fetched`() {
        // Given
        val captor = argumentCaptor<(Either<FailureBo, CharactersBo>) -> Unit>()
        // When
        viewModel.onViewCreated()
        // Then
        verify(mockBridge).getCharacters(
            scope = any(),
            isMore = eq(false),
            onResult = captor.capture()
        )
        verifyNoMoreInteractions(mockBridge)
        captor.firstValue.invoke(FailureBo.Unknown.left())

        Assert.assertTrue(getRenderState() is CharactersState.ShowError)
    }

    private fun getRenderState() =
        (viewModel.screenState.value as? ScreenState.Render<CharactersState>)?.renderState

    private fun getDummyCharactersBo() = CharactersBo(
        code = 200,
        `data` = DataBo(
            results = listOf(
                ResultBo(
                    id = 1011334,
                    name = "3-D Man",
                    description = "",
                    comics = ElementsBo(available = 0, items = listOf()),
                    events = ElementsBo(available = 0, items = listOf()),
                    series = ElementsBo(available = 0, items = listOf()),
                    stories = ElementsBo(available = 0, items = listOf()),
                    thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg",
                    urls = listOf()
                )
            )
        ),
        status = "Ok"
    )
}