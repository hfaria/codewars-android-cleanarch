package com.cleanarch.codewars.demo.data.repository.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    var username: String,
    var name: String,
    var timestamp: Int,
)