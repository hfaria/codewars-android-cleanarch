package com.cleanarch.codewars.demo.unit.data

import com.cleanarch.codewars.demo.data.NotFoundOutput
import com.cleanarch.codewars.demo.data.Output
import com.cleanarch.codewars.demo.data.SuccessOutput
import com.cleanarch.codewars.demo.data.repository.CacheRepository
import com.cleanarch.codewars.demo.data.repository.CachedQueryRepository
import com.cleanarch.codewars.demo.data.repository.Repository
import junit.framework.Assert.assertTrue
import org.junit.Test

data class MockData(val id: String)

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

class MockCacheRepository: CacheRepository<String, MockData> {
    val data: HashMap<String, MockData> = hashMapOf()

    override fun get(params: String): Output<MockData> {
        return data[params]?.let {
            SuccessOutput(it)
        } ?: run {
            NotFoundOutput()
        }
    }

    override fun put(obj: MockData): Output<Unit> {
        //data[obj.id] = obj.copy()
        //return SuccessOutput()
        TODO("Not yet implemented")
    }

    override fun delete(obj: MockData): Output<Unit> {
        //data.remove(obj.id)
        TODO("Not yet implemented")
    }

    override fun prune(count: Int): Output<Unit> {
        TODO("Not yet implemented")
    }
}

class CachedQueryRepositoryTest {

    @Test
    fun shouldPrioritizeValidCache() {
        val id = "ID1"
        val localFreshData  = MockData(id)
        val queryRepository = MockQueryRepository()
        val cacheRepository = MockCacheRepository()
        cacheRepository.data[id] = localFreshData
        val cachedQueryRepository = CachedQueryRepository(queryRepository, cacheRepository)
        val result = cachedQueryRepository.query(id) as SuccessOutput
        assertTrue(result.data.id == id)
    }
}