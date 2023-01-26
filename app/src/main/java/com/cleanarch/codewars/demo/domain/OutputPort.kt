package com.cleanarch.codewars.demo.domain

/*
 *  Generic Interactor Use Case Output Port
 */
interface OutputPort<in D> {
    fun onStartLoading()
    fun onStopLoading()
    fun onError(error: String)
    fun onSuccess(data: D)
}
