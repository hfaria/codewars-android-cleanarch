package com.cleanarch.codewars.demo.unit.data

import com.cleanarch.codewars.demo.data.repository.cache.CachePolicy
import com.cleanarch.codewars.demo.unit.data.mock.InMemoryMutableRepository
import org.junit.Test

class UserCacheRepositoryTest {

    @Test
    fun shouldFoo() {
        val policy = CachePolicy()
        val cacheRepository = InMemoryMutableRepository()
    }
}