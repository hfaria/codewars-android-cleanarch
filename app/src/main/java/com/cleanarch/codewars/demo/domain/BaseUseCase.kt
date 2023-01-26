package com.cleanarch.codewars.demo.domain

import com.cleanarch.codewars.demo.data.ErrorOutput
import com.cleanarch.codewars.demo.data.Output
import com.cleanarch.codewars.demo.data.SuccessOutput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<in INPUT, DATA> {

    protected abstract fun invoke(input: INPUT): Output<DATA>

    open suspend fun run(input: INPUT, port: OutputPort<DATA>) {
        port.onStartLoading()
        val response = withContext(Dispatchers.IO) {
            invoke(input)
        }
        port.onStopLoading()

        if (response is SuccessOutput<DATA>) {
            port.onSuccess(response.data)
        } else if (response is ErrorOutput<DATA>) {
            port.onError(response.error)
        }
        // TODO Add remaining Output scenarios
    }
}
