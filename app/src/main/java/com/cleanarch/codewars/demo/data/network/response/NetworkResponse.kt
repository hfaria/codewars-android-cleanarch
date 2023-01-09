package com.cleanarch.codewars.demo.data.network.response

sealed class NetworkResponse<T>(val code: Int)

// Error
class NetworkNotFoundResponse<T>(code: Int): NetworkResponse<T>(code)
class NetworkExceptionResponse<T>(code: Int, val message: String?): NetworkResponse<T>(code)
class NetworkErrorResponse<T>(code: Int, val message: String?): NetworkResponse<T>(code)

// Success
class NetworkEmptyResponse<T>(code: Int) : NetworkResponse<T>(code)
class NetworkSuccessResponse<T>(code: Int, val body: T) : NetworkResponse<T>(code)
