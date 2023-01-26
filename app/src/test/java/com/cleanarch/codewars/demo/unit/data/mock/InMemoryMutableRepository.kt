package com.cleanarch.codewars.demo.unit.data.mock

import com.cleanarch.codewars.demo.data.*
import com.cleanarch.codewars.demo.data.repository.MutableRepository

class InMemoryMutableRepository: MutableRepository<String, MockData> {
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