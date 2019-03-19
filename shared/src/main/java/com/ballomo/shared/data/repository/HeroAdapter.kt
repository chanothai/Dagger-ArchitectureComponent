package com.ballomo.shared.data.repository

import com.ballomo.shared.domain.hero.entity.HeroEntity
import io.reactivex.Observable

interface HeroAdapter {
    fun getAll(): Observable<HeroEntity>
    fun get()
}