package com.cleanarch.codewars.demo.domain

import com.cleanarch.codewars.demo.data.ErrorOutput
import com.cleanarch.codewars.demo.data.SuccessOutput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UseCaseRunner<INPUT, DATA> {
    suspend fun run(useCase: UseCase<INPUT, DATA>, input: INPUT, port: OutputPort<DATA>) {
        port.onStartLoading()
        val response = withContext(Dispatchers.IO) {
            useCase.run(input)
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