package com.emdp.domain.di

import com.emdp.domain.DomainContract
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.feature.CharactersDomainBridge
import com.emdp.domain.feature.CharactersDomainBridgeImpl
import com.emdp.domain.usecase.GetCharacterDetailUc
import com.emdp.domain.usecase.GetCharacterDetailUc.Companion.CHARACTER_DETAIL_TAG
import com.emdp.domain.usecase.GetCharactersUc
import com.emdp.domain.usecase.GetCharactersUc.Companion.CHARACTERS_LIST_TAG
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    // brige
    factory<CharactersDomainBridge<CharactersBo>> {
        CharactersDomainBridgeImpl(
            getCharactersUc = get(named(name = CHARACTERS_LIST_TAG)),
            getCharacterDetailUc = get(named(name = CHARACTER_DETAIL_TAG))
        )
    }
    // use-case
    factory<DomainContract.Presentation.UseCase<Nothing, CharactersBo>>(
        named(name = CHARACTERS_LIST_TAG)
    ) {
        GetCharactersUc(dataRepository = get())
    }
    factory<DomainContract.Presentation.UseCase<Nothing, CharactersBo>>(
        named(name = CHARACTER_DETAIL_TAG)
    ) {
        GetCharacterDetailUc(dataRepository = get())
    }
}