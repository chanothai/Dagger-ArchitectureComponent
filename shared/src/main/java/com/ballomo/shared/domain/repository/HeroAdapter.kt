package com.ballomo.shared.domain.repository

import androidx.lifecycle.LiveData
import com.ballomo.shared.domain.Result
import com.ballomo.shared.domain.entity.HeroEntity
import io.reactivex.Observable

interface HeroAdapter {
    fun getAll(): Observable<HeroEntity>
    fun get()
}