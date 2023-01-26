package com.cleanarch.codewars.demo.data.repository.cache

import com.cleanarch.codewars.demo.data.EmptyOutput
import com.cleanarch.codewars.demo.data.ExpiredOutput
import com.cleanarch.codewars.demo.data.Output
import com.cleanarch.codewars.demo.data.SuccessOutput
import com.cleanarch.codewars.demo.data.repository.CacheStorageRepository
import com.cleanarch.codewars.demo.data.repository.MutableRepository
import com.cleanarch.codewars.demo.domain.User

data class CachePolicy(val refreshRate: Long? = null, val limit: Int? = null)

class UserCacheRepository(
    private val storage: CacheStorageRepository<String, User>,
    private val cachePolicy: CachePolicy = CachePolicy()
): MutableRepository<String, User> {

    override fun put(obj: User): Output<Unit> {
        storage.put(obj)
        cachePolicy.limit?.let {
            storage.prune(it)
        }
        return EmptyOutput()
    }

    override fun get(params: String): Output<User> {
        val result = storage.get(params)

        if (result is SuccessOutput && cachePolicy.refreshRate != null) {
            val timeNow = System.currentTimeMillis()
            val elapsed = timeNow - result.data.timestamp

            if (elapsed > cachePolicy.refreshRate) {
                return ExpiredOutput(result.data)
            }
        }

        return result
    }
}