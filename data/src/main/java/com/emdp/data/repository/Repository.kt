package com.emdp.data.repository

import arrow.core.Either
import com.emdp.data.datasource.CharactersDataSource
import com.emdp.data.datasource.PaginationDataSource
import com.emdp.domain.DomainContract
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.domain.FailureBo

object Repository :
    DomainContract.Data.DataRepository<CharactersBo>,
    DomainContract.Pagination.PaginationRepository {

    lateinit var charactersDataSource: CharactersDataSource
    lateinit var paginationDataSource: PaginationDataSource

    private const val INIT_CHARACTERS: Int = 0
    private const val MORE_CHARACTERS: Int = 20

    override suspend fun getCharacters(isMore: Boolean): Either<FailureBo, CharactersBo> {
        if (isMore) newPage(getLastPage() + MORE_CHARACTERS) else newPage(INIT_CHARACTERS)
        return charactersDataSource.getCharactersResponse(getLastPage())
    }

    override suspend fun getCharacterDetail(id: Int): Either<FailureBo, CharactersBo> =
        charactersDataSource.getCharacterDetailResponse(id)

    override fun newPage(page: Int) {
        paginationDataSource.setPagination(page)
    }

    override fun getLastPage(): Int = paginationDataSource.getPagination()
}