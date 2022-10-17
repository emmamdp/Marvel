package com.emdp.marvel.presentation.domain

import com.emdp.domain.domain.ErrorMessage

sealed class FailureVo(val msg: String?) {

    companion object {
        private const val DEFAULT_STRING_RESOURCE = "none"
    }

    fun getErrorMessage(): String = msg ?: DEFAULT_STRING_RESOURCE

    object NoConnection : FailureVo(msg = ErrorMessage.ERROR_NO_CONNECTION)
    object NoData : FailureVo(msg = ErrorMessage.ERROR_NO_DATA)
    object Unknown : FailureVo(msg = ErrorMessage.ERROR_UNKNOWN)
    class Error(msg: String) : FailureVo(msg = msg)
}
