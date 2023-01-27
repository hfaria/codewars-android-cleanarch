package com.cleanarch.codewars.demo.domain

import com.cleanarch.codewars.demo.data.Output

interface UseCase<in INPUT, DATA> {
    fun run(input: INPUT): Output<DATA>
}
