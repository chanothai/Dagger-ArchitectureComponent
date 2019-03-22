package com.ballomo.shared.data.repository

import androidx.lifecycle.LiveData
import com.ballomo.shared.domain.Result
import com.ballomo.shared.domain.hero.entity.HeroEntity

interface HeroAdapter {
    fun getAll(): LiveData<Result<HeroEntity>>
    fun get()
//    fun getAllByPage(page: String): LiveD
}