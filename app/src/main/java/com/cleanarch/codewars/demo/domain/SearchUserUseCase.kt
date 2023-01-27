package com.cleanarch.codewars.demo.domain

import com.cleanarch.codewars.demo.data.ErrorOutput
import com.cleanarch.codewars.demo.data.Output
import com.cleanarch.codewars.demo.data.repository.cache.CachedQueryRepository

class SearchUserUseCase(
    private val repository: CachedQueryRepository<String, User>
): UseCase<String , User> {

    override fun run(username: String): Output<User> {
        if (username.isEmpty()) {
            return ErrorOutput("ERROR: Empty username")
        } else if (username.length < 3) {
            return ErrorOutput("ERROR: Username is too short")
        }

        return repository.query(username)
    }
}
