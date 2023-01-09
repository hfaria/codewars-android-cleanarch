package com.cleanarch.codewars.demo.data.repository

import com.cleanarch.codewars.demo.data.ExpiredOutput
import com.cleanarch.codewars.demo.data.Output
import com.cleanarch.codewars.demo.data.SuccessOutput

class CachedQueryRepository<P, D>(
    private val queryRepository: Repository<P, D>,
    private val cacheRepository: CacheRepository<P, D>
) {

    fun query(params: P): Output<D> {
        val cacheOutput = cacheRepository.get(params)
        return when (cacheOutput) {
            is SuccessOutput -> {
                cacheOutput
            }
            is ExpiredOutput -> {
                val queryOutput = queryRepository.get(params)
                if (queryOutput is SuccessOutput) {
                    cacheRepository.put(queryOutput.data)
                }
                return queryOutput
            }
            else -> {
                cacheOutput
            }
        }
    }
}