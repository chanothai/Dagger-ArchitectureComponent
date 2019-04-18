package com.ballomo.shared.domain

import androidx.lifecycle.MediatorLiveData

abstract class MediatorUseCase<in P, R> {
    protected val result = MediatorLiveData<R>()

    open fun observe(): MediatorLiveData<R> = result

    abstract fun execute(parameters: P)
}