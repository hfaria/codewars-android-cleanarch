package com.cleanarch.codewars.demo.unit.data.mock

import com.cleanarch.codewars.demo.data.*
import com.cleanarch.codewars.demo.data.repository.CacheStorageRepository
import com.cleanarch.codewars.demo.domain.User

class InMemoryUserRepository: CacheStorageRepository<String, User> {
    val data: HashMap<String, User> = hashMapOf()

    val pruneCallArgRecord = mutableListOf<Int>()

    override fun get(params: String): Output<User> {
        return data[params]?.let {
            if (it.timestamp == ALWAYS_EXPIRED.toLong()) {
                ExpiredOutput(it)
            } else {
                SuccessOutput(it)
            }
        } ?: run {
            NotFoundOutput()
        }
    }

    override fun put(obj: User): Output<Unit> {
        data[obj.username] = obj.copy()
        return EmptyOutput()
    }

    override fun prune(count: Int): Output<Unit> {
        pruneCallArgRecord.add(count)
        return EmptyOutput()
    }
}