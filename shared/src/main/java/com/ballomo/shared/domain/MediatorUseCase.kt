package com.ballomo.shared.domain

import androidx.lifecycle.MediatorLiveData

abstract class MediatorUseCase<in P, R> {
    protected val result = MediatorLiveData<Result<R>>()

    open fun observe(): MediatorLiveData<Result<R>> = result

    abstract fun executeLoadAll(parameters: P)
    abstract fun executeLoadByPage(parameters: P)
}