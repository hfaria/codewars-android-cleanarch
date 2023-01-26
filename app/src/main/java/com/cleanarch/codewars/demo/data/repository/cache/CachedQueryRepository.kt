package com.cleanarch.codewars.demo.data.repository.cache

import com.cleanarch.codewars.demo.data.*
import com.cleanarch.codewars.demo.data.repository.CacheRepository
import com.cleanarch.codewars.demo.data.repository.Repository

class CachedQueryRepository<P, D>(
    private val queryRepository: Repository<P, D>,
    private val cacheRepository: CacheRepository<P, D>
) {

    fun query(params: P, forceUpdate: Boolean = false): Output<D> {
        if (forceUpdate) {
            return actuallyQuery(params)
        }

        val cacheOutput = cacheRepository.get(params)
        return when (cacheOutput) {
            is ExpiredOutput,
            is NotFoundOutput,
            is EmptyOutput, -> {
                actuallyQuery(params)
            }
            else -> {
                cacheOutput
            }
        }
    }

    private fun actuallyQuery(params: P): Output<D> {
        val queryOutput = queryRepository.get(params)
        if (queryOutput is SuccessOutput) {
            cacheRepository.put(queryOutput.data)
        }
        return queryOutput
    }
}