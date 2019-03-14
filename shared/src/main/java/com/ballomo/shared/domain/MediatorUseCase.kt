package com.ballomo.shared.domain

import androidx.lifecycle.MediatorLiveData

abstract class MediatorUseCase<in P, R> {
    protected val result = MediatorLiveData<Result<R>>()

    open fun observe(): MediatorLiveData<Result<R>> = result

    abstract fun execute(parameters: P)

    abstract fun executeResponse(parameters: P): R
}