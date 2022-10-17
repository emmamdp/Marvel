package com.emdp.domain

import arrow.core.Either
import com.emdp.domain.domain.FailureBo
import kotlinx.coroutines.*

interface DomainContract {

    interface Data {
        interface DataRepository<out T> {
            suspend fun fetchCharacters(): Either<FailureBo, T>
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