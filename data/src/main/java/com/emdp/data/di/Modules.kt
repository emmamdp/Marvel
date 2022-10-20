package com.emdp.data.di

import com.emdp.data.datasource.*
import com.emdp.data.datasource.ConnectivityDataSource.Companion.CONNECTIVITY_DATA_SOURCE_TAG
import com.emdp.data.repository.Repository
import com.emdp.data.utils.ConnectivityInterceptor
import com.emdp.data.utils.ConnectivityInterceptor.Companion.CONNECTIVITY_INTERCEPTOR_TAG
import com.emdp.domain.DomainContract
import com.emdp.domain.domain.CharactersBo
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    single {
        Repository.apply {
            charactersDataSource = get()
            paginationDataSource = get()
        }
    }
    single<DomainContract.Data.DataRepository<CharactersBo>> { get<Repository>() }
    single<DomainContract.Pagination.PaginationRepository> { get<Repository>() }

    factory<PaginationDataSource> { AndroidDataSource(androidContext()) }
    factory<ConnectivityDataSource> { AndroidDataSource(androidContext()) }
    factory<CharactersDataSource> { CharactersDataSourceImpl(get()) }

    factory<Interceptor>(named(name = CONNECTIVITY_INTERCEPTOR_TAG)) {
        ConnectivityInterceptor(get(named(name = CONNECTIVITY_DATA_SOURCE_TAG)))
    }
    factory<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(20L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(ConnectivityInterceptor(get()))
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get())
            .baseUrl("https://gateway.marvel.com/")
            .build()
    }
}