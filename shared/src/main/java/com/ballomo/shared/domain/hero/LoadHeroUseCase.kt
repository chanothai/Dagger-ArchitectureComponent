package com.ballomo.shared.domain.hero

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.ballomo.shared.data.repository.HeroAdapter
import com.ballomo.shared.domain.DefaultScheduler
import com.ballomo.shared.domain.MediatorUseCase
import com.ballomo.shared.domain.Result
import com.ballomo.shared.domain.hero.entity.HeroEntity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class LoadHeroUseCase @Inject constructor(
    private val heroRepo: HeroAdapter
) : MediatorUseCase<Any, ListHeroInformation>() {

    override fun execute(parameters: Any){
        val heroSessionObservable = heroRepo.getAll()

        result.removeSource(heroSessionObservable)
        result.addSource(heroSessionObservable) {
            when(it) {
                is Result.Success -> {
                    val information = arrayListOf<LoadHeroInformation>()
                    it.data.results.forEach {result->
                        information.add(
                            LoadHeroInformation(
                                result.name ?: "",
                                result.image ?: ""
                            )
                        )
                    }

                    result.postValue(Result.Success(ListHeroInformation(information)))
                }

                is Result.Error -> {
                    result.postValue(it)
                }
            }
        }
    }
}

data class LoadHeroInformation(
    var name: String,
    var image: String
)

data class ListHeroInformation(
    var heroInformation: List<LoadHeroInformation>
)

data class PageListHeroInformation(
    var heroInformation: LiveData<PagedList<HeroEntity>>
)

