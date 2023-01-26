package com.cleanarch.codewars.demo.domain

import com.cleanarch.codewars.demo.data.ErrorOutput
import com.cleanarch.codewars.demo.data.Output
import com.cleanarch.codewars.demo.data.repository.cache.CachedQueryRepository

data class SearchUserInput(
    val username: String,
    val forceUpdate: Boolean = false
)

class SearchUserUseCase(
    private val repository: CachedQueryRepository<String, User>
): BaseUseCase<SearchUserInput, User>() {
    override fun invoke(input: SearchUserInput): Output<User> {
        val username = input.username
        if (username.isEmpty()) {
            return ErrorOutput("ERROR: Empty username")
        } else if (username.length < 3) {
            return ErrorOutput("ERROR: Username is too short")
        }

        return repository.query(input.username, input.forceUpdate)
    }
}
