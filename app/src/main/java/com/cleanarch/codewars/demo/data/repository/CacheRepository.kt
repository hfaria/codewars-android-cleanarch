package com.cleanarch.codewars.demo.data.repository

import com.cleanarch.codewars.demo.data.Output

interface CacheRepository<P, D>: MutableRepository<P, D> {
    fun prune(count: Int): Output<Unit>
}