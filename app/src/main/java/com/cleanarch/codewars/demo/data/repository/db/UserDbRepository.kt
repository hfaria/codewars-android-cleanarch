package com.cleanarch.codewars.demo.data.repository.db

import com.cleanarch.codewars.demo.data.NotFoundOutput
import com.cleanarch.codewars.demo.data.Output
import com.cleanarch.codewars.demo.data.SuccessOutput
import com.cleanarch.codewars.demo.data.db.dao.UserDao
import com.cleanarch.codewars.demo.data.db.entity.UserEntity
import com.cleanarch.codewars.demo.data.repository.Mapper
import com.cleanarch.codewars.demo.data.repository.MutableRepository
import com.cleanarch.codewars.demo.domain.User

class UserDbRepository(private val dao: UserDao): MutableRepository<String, User> {

    private val mapper = Mapper<UserEntity, User> {
        User(
            it.username,
            it.name
        )
    }

    override fun get(params: String): Output<User> {
        return dao.getByUsername(params)?.let {
            SuccessOutput(mapper.map(it))
        } ?: run {
            NotFoundOutput()
        }
    }

    override fun put(obj: User): Output<Unit> {
        TODO("Not yet implemented")
    }
}