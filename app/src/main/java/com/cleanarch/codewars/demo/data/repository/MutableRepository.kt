package com.cleanarch.codewars.demo.data.repository

import com.cleanarch.codewars.demo.data.Output

interface MutableRepository<P, D>: Repository<P, D> {
    fun put(obj: D): Output<Unit>
}