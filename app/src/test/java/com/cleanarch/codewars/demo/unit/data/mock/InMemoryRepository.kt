package com.cleanarch.codewars.demo.unit.data.mock

import com.cleanarch.codewars.demo.data.NotFoundOutput
import com.cleanarch.codewars.demo.data.Output
import com.cleanarch.codewars.demo.data.SuccessOutput
import com.cleanarch.codewars.demo.data.repository.Repository

class InMemoryRepository: Repository<String, MockData> {

    val data: HashMap<String, MockData> = hashMapOf()

    override fun get(params: String): Output<MockData> {
        return data[params]?.let {
            SuccessOutput(it)
        } ?: run {
            NotFoundOutput()
        }
    }
}