package com.emdp.marvel.presentation.feature.detail.viewmodel

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.emdp.domain.domain.*
import com.emdp.domain.feature.CharactersDomainBridge
import com.emdp.marvel.presentation.base.ScreenState
import com.emdp.marvel.presentation.di.presentationModule
import com.emdp.marvel.presentation.feature.detail.view.state.DetailState
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

internal class DetailViewModelTest : KoinTest {

    private val viewModel: DetailViewModel by inject()
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
    fun `check that state is 'ShowCharacterDetail' when character is fetched`() {
        // Given
        val captor = argumentCaptor<(Either<FailureBo, CharactersBo>) -> Unit>()
        // When
        viewModel.onViewCreated(1011334)
        // Then
        verify(mockBridge).getCharacterDetail(
            scope = any(),
            id = eq(1011334),
            onResult = captor.capture()
        )
        verifyNoMoreInteractions(mockBridge)
        captor.firstValue.invoke(getDummyCharactersBo().right())

        Assert.assertTrue(getRenderState() is DetailState.ShowCharacterDetail)
    }

    @Test
    fun `check that state is 'ShowError' when character cannot be fetched`() {
        // Given
        val captor = argumentCaptor<(Either<FailureBo, CharactersBo>) -> Unit>()
        // When
        viewModel.onViewCreated(1011334)
        // Then
        verify(mockBridge).getCharacterDetail(
            scope = any(),
            id = eq(1011334),
            onResult = captor.capture()
        )
        verifyNoMoreInteractions(mockBridge)
        captor.firstValue.invoke(FailureBo.Unknown.left())

        Assert.assertTrue(getRenderState() is DetailState.ShowError)
    }

    private fun getRenderState() =
        (viewModel.screenState.value as? ScreenState.Render<DetailState>)?.renderState

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