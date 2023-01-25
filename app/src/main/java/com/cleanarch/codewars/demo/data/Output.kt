package com.cleanarch.codewars.demo.data

import com.cleanarch.codewars.demo.data.repository.network.Mapper

/*
    Data class used to encapsulate any response
    to a data request.

    Could be used by any kind of data source
    to represent the result of a given data request.
 */
sealed class Output<T> {

    fun <R> map(mapper: Mapper<T, R>): Output<R> {
        return when (this) {
            is SuccessOutput -> {
                SuccessOutput(mapper.map(this.data))
            }
            is NotFoundOutput -> {
                NotFoundOutput()
            }
            is ErrorOutput -> {
                ErrorOutput(this.error)
            }
            else -> {
                ThrowableOutput((this as ThrowableOutput).data)
            }
        }
    }
}

data class SuccessOutput<T>(val data: T) : Output<T>()
data class ExpiredOutput<T>(val data: T) : Output<T>()
class EmptyOutput<T>: Output<T>()
class NotFoundOutput<T>: Output<T>()
data class ErrorOutput<T>(val error: String) : Output<T>()
data class ThrowableOutput<T>(val data: Throwable) : Output<T>()
