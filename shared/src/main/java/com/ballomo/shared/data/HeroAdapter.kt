package com.ballomo.shared.data

import androidx.lifecycle.LiveData
import com.ballomo.shared.data.entity.HeroEntity
import com.ballomo.shared.data.entity.hero.Results
import com.ballomo.shared.domain.Result

interface HeroAdapter {
    fun getAll(): LiveData<Result<HeroEntity>>
    fun get()
    fun getByPage(pageSize: Int): LiveData<Listing<Results>>
}