package com.cleanarch.codewars.demo.data.repository

fun interface Mapper<S, T> {
    fun map (source: S): T
}
