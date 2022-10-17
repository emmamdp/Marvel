package com.emdp.data.datasource.error

import android.util.Log
import arrow.core.Either
import arrow.core.left
import com.emdp.data.domain.FailureDto
import com.emdp.data.domain.dtoToBo
import com.emdp.data.utils.NoConnectivityException
import com.emdp.domain.domain.ErrorMessage
import com.emdp.domain.domain.FailureBo
import okio.IOException
import retrofit2.Response

class DefaultHandlerDataSourceError : HandlerDataSourceError {
    override fun <T> handleDataSourceError(response: Response<T>): Either<FailureBo, Nothing> =
        when (response.code()) {
            in 400..499 -> FailureDto.RequestError(code = 400, msg = ErrorMessage.ERROR_BAD_REQUEST)
            in 500..599 -> FailureDto.RequestError(code = 500, msg = ErrorMessage.ERROR_SERVER)
            else -> FailureDto.Unknown
        }.dtoToBo().left()

    override fun handleDataSourceException(exception: Exception): Either<FailureBo, Nothing> =
        when (exception) {
            is NoConnectivityException -> FailureDto.NoConnection
            is IOException -> FailureDto.ServerError(exception.message ?: "")
            else -> {
                Log.e("handleException", "Error: ${exception.message}")
                FailureDto.Unknown
            }
        }.dtoToBo().left()
}