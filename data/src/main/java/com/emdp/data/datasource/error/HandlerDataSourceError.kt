package com.emdp.data.datasource.error

import arrow.core.Either
import com.emdp.domain.domain.FailureBo
import retrofit2.Response

interface HandlerDataSourceError {
    fun <T> handleDataSourceError(response: Response<T>): Either<FailureBo, Nothing>
    fun handleDataSourceException(exception: Exception): Either<FailureBo, Nothing>
}