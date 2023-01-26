package com.cleanarch.codewars.demo.unit.data

import com.cleanarch.codewars.demo.data.*
import com.cleanarch.codewars.demo.data.repository.cache.CachedQueryRepository
import com.cleanarch.codewars.demo.unit.data.mock.ALWAYS_EXPIRED
import com.cleanarch.codewars.demo.unit.data.mock.InMemoryMutableRepository
import com.cleanarch.codewars.demo.unit.data.mock.MockData
import com.cleanarch.codewars.demo.unit.data.mock.InMemoryRepository
import junit.framework.Assert.assertEquals
import org.junit.Test

class CachedQueryRepositoryTest {

    @Test
    fun shouldPrioritizeValidCache() {
        val id = "ID1"
        val queryRepository = InMemoryRepository()
        val cacheRepository = InMemoryMutableRepository()
        val cacheData = MockData(id)
        cacheRepository.data[id] = cacheData
        val cachedQueryRepository = CachedQueryRepository(queryRepository, cacheRepository)
        val result = cachedQueryRepository.query(id) as SuccessOutput
        assertEquals(cacheData, result.data)
    }

    @Test
    fun shouldGetFreshDataIfExpiredCache() {
        val id = "ID1"
        val freshScore = 200
        val oldScore   = 150

        // Query Repo
        val queryData = MockData(id, freshScore)
        val queryRepository = InMemoryRepository()
        queryRepository.data[id] = queryData

        // Cache Repo
        val cacheRepository = InMemoryMutableRepository()
        cacheRepository.data[id] = MockData(id, oldScore, savedAt = ALWAYS_EXPIRED)

        // Test
        val cachedQueryRepository = CachedQueryRepository(queryRepository, cacheRepository)
        val result = cachedQueryRepository.query(id) as SuccessOutput
        assertEquals(queryData, result.data)
        assertEquals(queryData, cacheRepository.data[id]!!)
    }

    @Test
    fun shouldGetFreshDataIfForceUpdate() {
        val id = "ID1"
        val freshScore = 200
        val oldScore   = 150

        // Query Repo
        val queryData = MockData(id, freshScore)
        val queryRepository = InMemoryRepository()
        queryRepository.data[id] = queryData

        // Cache Repo
        val cacheRepository = InMemoryMutableRepository()
        cacheRepository.data[id] = MockData(id, oldScore)

        // Test
        val cachedQueryRepository = CachedQueryRepository(queryRepository, cacheRepository)
        val result = cachedQueryRepository.query(id, forceUpdate = true) as SuccessOutput
        assertEquals(queryData, result.data)
        assertEquals(queryData, cacheRepository.data[id]!!)
    }

    @Test
    fun shouldGetFreshDataIfCacheMiss() {
        val id = "ID1"
        val freshScore = 200

        // Query Repo
        val queryData = MockData(id, freshScore)
        val queryRepository = InMemoryRepository()
        queryRepository.data[id] = queryData

        // Cache Repo
        val cacheRepository = InMemoryMutableRepository()

        // Test
        val cachedQueryRepository = CachedQueryRepository(queryRepository, cacheRepository)
        val result = cachedQueryRepository.query(id) as SuccessOutput
        assertEquals(queryData, result.data)
        assertEquals(queryData, cacheRepository.data[id]!!)
    }
}