package com.cleanarch.codewars.demo.data.repository.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.cleanarch.codewars.demo.data.repository.db.entity.UserEntity
import com.hfaria.portfolio.codewars.persistence.local.dao.BaseDao

@Dao
interface UserDao: BaseDao<UserEntity> {
    @Query("SELECT * from user")
    fun getAll(): List<UserEntity>

    @Query("SELECT * from user WHERE :username = username")
    fun getByUsername(username: String): UserEntity?

    @Query("DELETE from user")
    fun deleteAll()
}