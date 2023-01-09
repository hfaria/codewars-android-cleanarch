package com.cleanarch.codewars.demo.data

/*
    Data class used to encapsulate any response
    to a data request.

    Could be used by any kind of data source
    to represent the result of a given data request.
 */
sealed class Output<out T> {

    fun <R> map(mapper: (T) -> (R)): Output<R> {
        return when (this) {
            is SuccessOutput -> {
                SuccessOutput(mapper.invoke(this.data))
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

data class SuccessOutput<out T>(val data: T) : Output<T>()
data class ExpiredOutput<out T>(val data: T) : Output<T>()
class EmptyOutput<out T>: Output<T>()
class NotFoundOutput<out T>: Output<T>()
data class ErrorOutput<out T>(val error: String) : Output<T>()
data class ThrowableOutput<out T>(val data: Throwable) : Output<T>()
