package com.cleanarch.codewars.demo.unit.data

import com.cleanarch.codewars.demo.data.ExpiredOutput
import com.cleanarch.codewars.demo.data.repository.cache.CachePolicy
import com.cleanarch.codewars.demo.data.repository.cache.UserCacheRepository
import com.cleanarch.codewars.demo.domain.User
import com.cleanarch.codewars.demo.unit.data.mock.InMemoryUserRepository
import com.cleanarch.codewars.demo.unit.data.mock.OLD_RECORD
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.util.concurrent.TimeUnit

class UserCacheRepositoryTest {

    @Test
    fun shouldPruneDataIfCacheLimitReached() {
        // Prepare data
        val usr1 = User("uid1")
        val usr2 = User("uid2")
        val usr3 = User("uid3")
        val cacheLimit = 2
        val policy = CachePolicy(limit = cacheLimit)

        // Populate storage
        val storageRepo = InMemoryUserRepository()
        storageRepo.data["uid1"] = usr1
        storageRepo.data["uid2"] = usr2

        // Try to add a new user
        val cache = UserCacheRepository(storageRepo, policy)
        cache.put(usr3)

        // Assert that "prune()" function was called
        assertTrue(storageRepo.pruneCallArgRecord.size == 1)
        assertTrue(storageRepo.pruneCallArgRecord[0] == cacheLimit)
    }

    @Test
    fun shouldReturnExpiredOutputIfOldUserIsFound() {
        // Prepare data
        val usr1 = User("uid1", timestamp = OLD_RECORD)
        val policy = CachePolicy(refreshRate = TimeUnit.SECONDS.toMillis(1))

        // Populate storage
        val storageRepo = InMemoryUserRepository()
        storageRepo.data["uid1"] = usr1

        // Try to add a new user
        val cache  = UserCacheRepository(storageRepo, policy)
        val result = cache.get("uid1")

        // Assert that an ExpiredOutput response was returned
        assertTrue(result is ExpiredOutput)
    }
}