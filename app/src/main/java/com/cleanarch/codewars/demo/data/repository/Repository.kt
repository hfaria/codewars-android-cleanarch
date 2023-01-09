package com.cleanarch.codewars.demo.data.repository

import com.cleanarch.codewars.demo.data.Output

interface Repository<P, D> {
    fun get(params: P): Output<D>
}