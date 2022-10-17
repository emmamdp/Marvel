package com.emdp.domain.di

import com.emdp.domain.DomainContract
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.feature.CharactersDomainBridge
import com.emdp.domain.feature.CharactersDomainBridgeImpl
import com.emdp.domain.usecase.FetchCharactersUc
import org.koin.dsl.module

val domainModule = module {
    // brige
    factory<CharactersDomainBridge<CharactersBo>> {
        CharactersDomainBridgeImpl(fetchCharactersUc = get())
    }
    // use-case
    factory<DomainContract.Presentation.UseCase<Nothing, CharactersBo>> {
        FetchCharactersUc(dataRepository = get())
    }
}