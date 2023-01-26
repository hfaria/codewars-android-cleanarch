package com.cleanarch.codewars.demo.unit.data

import com.cleanarch.codewars.demo.data.*
import com.cleanarch.codewars.demo.data.repository.MutableRepository
import com.cleanarch.codewars.demo.data.repository.cache.CachedQueryRepository
import com.cleanarch.codewars.demo.data.repository.Repository
import junit.framework.Assert.assertEquals
import org.junit.Test

const val ALWAYS_EXPIRED = -500
data class MockData(val id: String, val score: Int = 0, val savedAt: Int = 0)

class MockQueryRepository: Repository<String, MockData> {

    val data: HashMap<String, MockData> = hashMapOf()

    override fun get(params: String): Output<MockData> {
        return data[params]?.let {
            SuccessOutput(it)
        } ?: run {
            NotFoundOutput()
        }
    }
}

class MockCacheRepository: MutableRepository<String, MockData> {
    val data: HashMap<String, MockData> = hashMapOf()

    override fun get(params: String): Output<MockData> {
        return data[params]?.let {
            if (it.savedAt == ALWAYS_EXPIRED) {
                ExpiredOutput(it)
            } else {
                SuccessOutput(it)
            }
        } ?: run {
            NotFoundOutput()
        }
    }

    override fun put(obj: MockData): Output<Unit> {
        data[obj.id] = obj.copy()
        return EmptyOutput()
    }
}

class CachedQueryRepositoryTest {

    @Test
    fun shouldPrioritizeValidCache() {
        val id = "ID1"
        val queryRepository = MockQueryRepository()
        val cacheRepository = MockCacheRepository()
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
        val queryRepository = MockQueryRepository()
        queryRepository.data[id] = queryData

        // Cache Repo
        val cacheRepository = MockCacheRepository()
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
        val queryRepository = MockQueryRepository()
        queryRepository.data[id] = queryData

        // Cache Repo
        val cacheRepository = MockCacheRepository()
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
        val queryRepository = MockQueryRepository()
        queryRepository.data[id] = queryData

        // Cache Repo
        val cacheRepository = MockCacheRepository()

        // Test
        val cachedQueryRepository = CachedQueryRepository(queryRepository, cacheRepository)
        val result = cachedQueryRepository.query(id) as SuccessOutput
        assertEquals(queryData, result.data)
        assertEquals(queryData, cacheRepository.data[id]!!)
    }
}