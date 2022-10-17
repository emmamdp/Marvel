package com.emdp.data.domain

import com.emdp.domain.domain.ErrorMessage
import okhttp3.ResponseBody

sealed class FailureDto(val msg: String?) {

    companion object {
        private const val DEFAULT_ERROR_CODE = -1
    }

    object NoConnection : FailureDto(msg = ErrorMessage.ERROR_NO_CONNECTION)
    object NoData : FailureDto(msg = ErrorMessage.ERROR_NO_DATA)
    object Unknown : FailureDto(msg = ErrorMessage.ERROR_UNKNOWN)
    class RequestError(
        val code: Int = DEFAULT_ERROR_CODE,
        msg: String,
        val errorBody: ResponseBody? = null
    ) : FailureDto(msg = msg)

    class ServerError(msg: String) : FailureDto(msg = msg)
    class Error(msg: String?) : FailureDto(msg = msg)
}