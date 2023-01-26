package com.cleanarch.codewars.demo.data.repository.db.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cleanarch.codewars.demo.data.repository.db.dao.UserDao
import com.cleanarch.codewars.demo.data.repository.db.entity.UserEntity

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "codewars-database"

@Database(
    entities = [
        UserEntity::class,
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}
