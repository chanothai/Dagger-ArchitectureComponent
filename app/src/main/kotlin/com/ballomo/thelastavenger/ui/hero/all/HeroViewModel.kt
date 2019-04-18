package com.ballomo.thelastavenger.ui.hero.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ballomo.shared.domain.Result
import com.ballomo.thelastavenger.domain.LoadHeroUseCase
import com.ballomo.shared.util.map
import com.ballomo.thelastavenger.ui.hero.all.PostHeroListAdapter
import com.ballomo.thelastavenger.ui.hero.model.ListHeroInformation
import javax.inject.Inject

class HeroViewModel (
    private val loadHeroUseCase: LoadHeroUseCase
) : ViewModel() {
    val postHeroListAdapter by lazy { PostHeroListAdapter() }
    val loadingVisibility by lazy { MutableLiveData<Int>() }

    val loadHeroResult: LiveData<ListHeroInformation> by lazy {
        loadHeroUseCase.observe().map {
            it
        }
    }

    fun loadHeroInformation() {
        loadHeroUseCase.execute(Unit)
    }
}