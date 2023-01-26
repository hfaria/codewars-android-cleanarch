package com.cleanarch.codewars.demo.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class BaseScreenState {

    val errorMessage: LiveData<String>
        get() = _errorMessage
    protected val _errorMessage = MutableLiveData<String>()

    val errorId: LiveData<Int>
        get() = _errorId
    protected val _errorId = MutableLiveData<Int>()

    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val _isLoading = MutableLiveData<Boolean>()

    fun showErrorMessage(message: String) {
        _errorMessage.value = message
    }

    fun showErrorMessage(errorId: Int) {
        _errorId.value = errorId
    }

    fun setLoadingState(state: Boolean) {
        _isLoading.value = state
    }
}