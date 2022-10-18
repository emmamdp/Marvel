package com.emdp.domain

import arrow.core.Either
import com.emdp.domain.domain.FailureBo
import kotlinx.coroutines.*

interface DomainContract {

    interface Data {
        interface DataRepository<out T> {
            suspend fun getCharacters(isMore: Boolean): Either<FailureBo, T>
        }
    }

    interface Pagination {
        interface PaginationRepository {
            fun newPage(page: Int)
            fun getLastPage(): Int
        }
    }

    interface Presentation {
        interface UseCase<in T, out S> {
            fun invoke(
                scope: CoroutineScope,
                params: T? = null,
                onResult: (Either<FailureBo, S>) -> Unit,
                dispatcherWorker: CoroutineDispatcher = Dispatchers.IO
            ) {
                scope.launch { withContext(dispatcherWorker) { onResult(run(params)) } }
            }

            suspend fun run(params: T? = null): Either<FailureBo, S>
        }
    }
}