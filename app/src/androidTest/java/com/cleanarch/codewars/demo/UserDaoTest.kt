package com.cleanarch.codewars.demo

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.cleanarch.codewars.demo.data.repository.db.db.AppDatabase
import com.cleanarch.codewars.demo.data.repository.db.entity.UserEntity
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

// TODO add dependency injection
@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    val db = Room
        .inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
        .fallbackToDestructiveMigration()
        .build()
    val userDao = db.userDao()

    @Test
    fun testInsertUser() = runBlocking {
        val user = UserEntity(username = "g964", name = "", timestamp = 0)
        userDao.insert(user)
        val users = userDao.getAll()
        assertEquals(users.size, 1)
        assertEquals(users[0].username, "g964")
    }
}