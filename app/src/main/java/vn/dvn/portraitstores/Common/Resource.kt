package vn.dvn.portraitstores.Common

import vn.dvn.portraitstores.data.data_source.remote.dto.RegisterDto

sealed class Resource<T>(val data: T?= null, val message: String?= null ) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(data: T? = null, message: String): Resource<T>(data, message)

    class Loading<T>(data: T? = null): Resource<T>(data)
}