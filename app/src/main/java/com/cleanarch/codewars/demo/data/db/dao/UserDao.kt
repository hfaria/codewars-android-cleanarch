package com.cleanarch.codewars.demo.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.cleanarch.codewars.demo.data.db.entity.UserEntity

@Dao
interface UserDao: BaseDao<UserEntity> {
    @Query("SELECT * from user")
    fun getAll(): List<UserEntity>

    @Query("SELECT * from user WHERE :username = username")
    fun getByUsername(username: String): UserEntity?

    @Query("DELETE from user")
    fun deleteAll()
}