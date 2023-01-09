package com.cleanarch.codewars.demo.data.repository

import com.cleanarch.codewars.demo.data.Output

class CachedQueryRepository<P, D>(
    val queryRepository: Repository<P, D>,
    val cacheRepository: MutableRepository<P, D>) {

    fun query(params: P): Output<D> {
        return cacheRepository.get(params)
    }
}