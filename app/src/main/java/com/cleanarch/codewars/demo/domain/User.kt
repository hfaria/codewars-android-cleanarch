package com.cleanarch.codewars.demo.domain

data class User(
    val username: String,
    val name: String?   = null,
    val timestamp: Long = 0
)