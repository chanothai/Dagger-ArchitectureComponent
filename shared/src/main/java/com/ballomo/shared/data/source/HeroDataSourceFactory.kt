package com.ballomo.shared.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ballomo.shared.data.api.HeroApi
import com.ballomo.shared.data.entity.hero.Results

class HeroDataSourceFactory(
    private val heroAPI: HeroApi
) : DataSource.Factory<Int, Results>() {

    val sourceLiveData = MutableLiveData<PageHeroDataSource>()

    override fun create(): DataSource<Int, Results>
    {
        val source = PageHeroDataSource(heroAPI)
        sourceLiveData.postValue(source)
        return source
    }
}