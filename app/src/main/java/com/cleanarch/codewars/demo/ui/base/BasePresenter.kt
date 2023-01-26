package com.cleanarch.codewars.demo.ui.base

import com.cleanarch.codewars.demo.domain.OutputPort

abstract class BasePresenter<D>(
    protected val state: BaseScreenState
): OutputPort<D> {

    override fun onError(error: String) {
        state.showErrorMessage(error)
    }

    override fun onStartLoading() {
        state.setLoadingState(true)
    }

    override fun onStopLoading() {
        state.setLoadingState(false)
    }
}