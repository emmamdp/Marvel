package com.emdp.data.utils

import arrow.core.Either
import arrow.core.right
import com.emdp.data.datasource.error.DefaultHandlerDataSourceError
import com.emdp.data.datasource.error.HandlerDataSourceError
import com.emdp.domain.domain.FailureBo
import retrofit2.Response


internal suspend fun <S : Response<T>, T, R> retrofitCharactersSafeCall(
    retrofitRequest: suspend (ts: Int, apkikey: String, hash: String, offset: Int, limit: Int) -> S,
    ts: Int,
    apkikey: String,
    hash: String,
    offset: Int,
    limit: Int,
    transform: (T) -> R,
    handlerDataSourceError: HandlerDataSourceError = DefaultHandlerDataSourceError()
): Either<FailureBo, R> =
    try {
        val response = retrofitRequest(ts, apkikey, hash, offset, limit)
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

internal suspend fun <S : Response<T>, T, R> retrofitCharacterSafeCall(
    retrofitRequest: suspend (id: Int, ts: Int, apkikey: String, hash: String) -> S,
    ts: Int,
    apkikey: String,
    hash: String,
    id: Int,
    transform: (T) -> R,
    handlerDataSourceError: HandlerDataSourceError = DefaultHandlerDataSourceError()
): Either<FailureBo, R> =
    try {
        val response = retrofitRequest(id, ts, apkikey, hash)
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