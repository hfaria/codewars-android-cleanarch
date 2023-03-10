package com.cleanarch.codewars.demo.data.repository.network

import com.cleanarch.codewars.demo.data.Output
import com.cleanarch.codewars.demo.data.network.response.GetUserResponse
import com.cleanarch.codewars.demo.data.network.retrofit.CodeWarsApi
import com.cleanarch.codewars.demo.data.repository.Mapper
import com.cleanarch.codewars.demo.data.repository.Repository
import com.cleanarch.codewars.demo.domain.User

class UserNetworkRepository(private val api: CodeWarsApi): Repository<String, User> {

    private val mapper = Mapper<GetUserResponse, User> {
        User(
            it.username,
            it.name
        )
    }

    override fun get(params: String): Output<User> {
        return api.getUsers(params).map(mapper)
    }
}