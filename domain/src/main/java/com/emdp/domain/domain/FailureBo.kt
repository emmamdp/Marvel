package com.emdp.domain.domain

sealed class FailureBo(var msg: String = "none") {
    object NoConnection : FailureBo(msg = ErrorMessage.ERROR_NO_CONNECTION)
    object NoData : FailureBo(msg = ErrorMessage.ERROR_NO_DATA)
    object Unknown : FailureBo(msg = ErrorMessage.ERROR_UNKNOWN)
    class InputParamsError(msg: String) : FailureBo(msg = msg)
    class RequestError(msg: String) : FailureBo(msg = msg)
    class ServerError(msg: String) : FailureBo(msg = msg)
    class Error(msg: String) : FailureBo(msg = msg)
}
