package com.emdp.data.utils

import arrow.core.Either
import arrow.core.right
import com.emdp.data.datasource.error.DefaultHandlerDataSourceError
import com.emdp.data.datasource.error.HandlerDataSourceError
import com.emdp.domain.domain.FailureBo
import retrofit2.Response


internal suspend fun <S : Response<T>, T, R> retrofitSafeCall(
    retrofitRequest: suspend () -> S,
    transform: (T) -> R,
    handlerDataSourceError: HandlerDataSourceError = DefaultHandlerDataSourceError()
): Either<FailureBo, R> =
    try {
        val response = retrofitRequest()
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                transform(body).right()
            } ?: run {
                handlerDataSourceError.handleDataSourceError(response)
            }
        } else {
            handlerDataSourceError.handleDataSourceError(response)
        }
    } catch (ex: Exception) {
        handlerDataSourceError.handleDataSourceException(ex)
    }