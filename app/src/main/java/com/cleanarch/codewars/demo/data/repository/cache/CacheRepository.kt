package com.cleanarch.codewars.demo.data.repository.cache

import com.cleanarch.codewars.demo.data.Output
import com.cleanarch.codewars.demo.data.repository.MutableRepository
import com.cleanarch.codewars.demo.domain.User

data class CachePolicy(val foo: Int = 1)

class UserCacheRepository(
    private val storage: MutableRepository<String, User>,
    private val cachePolicy: CachePolicy = CachePolicy()
): MutableRepository<String, User> {

    override fun put(obj: User): Output<Unit> {
        TODO("Not yet implemented")
    }

    override fun get(params: String): Output<User> {
        TODO("Not yet implemented")
    }


}