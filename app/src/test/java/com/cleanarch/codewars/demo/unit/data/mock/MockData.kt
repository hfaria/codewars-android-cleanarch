package com.cleanarch.codewars.demo.unit.data.mock

const val ALWAYS_EXPIRED = -500
const val OLD_RECORD = 0L
data class MockData(val id: String, val score: Int = 0, val savedAt: Int = 0)