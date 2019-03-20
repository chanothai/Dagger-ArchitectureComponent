package com.ballomo.thelastavenger.ui.hero

import com.ballomo.shared.domain.hero.ListHeroInformation
import com.ballomo.shared.domain.hero.LoadHeroInformation
import com.ballomo.shared.domain.hero.LoadHeroUseCase
import com.ballomo.shared.result.Event
import com.ballomo.thelastavenger.baseTest.BaseTest
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.lang.NullPointerException

class HeroViewModelTest: BaseTest() {

    @Mock
    lateinit var loadHeroUseCase: LoadHeroUseCase

    private lateinit var heroViewModel: HeroViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        heroViewModel = HeroViewModel(loadHeroUseCase)
    }

    @Test
    fun `load hero success`() {
        val expResponse = ListHeroInformation(
            arrayListOf(
                LoadHeroInformation(
                    "BallOMO",
                    "img2.jpg"
                )
            )
        )

        `when`(loadHeroUseCase.execute(Unit)).thenReturn(Observable.just(expResponse))

        heroViewModel.loadHeroInformation()

        val result = heroViewModel.postHeroListAdapter.heroList

        Assert.assertEquals(expResponse, result)

        verify(loadHeroUseCase).execute(Unit)
    }

    @Test
    fun `load hero then error`() {
        val error = NullPointerException("Hero was null")
        val errorExp = Event(error.message).getContentIfNotHandled()

        `when`(loadHeroUseCase.execute(Unit)).thenReturn(Observable.error(error))

        heroViewModel.loadHeroInformation()


        val result = heroViewModel.messageLiveData.value?.getContentIfNotHandled()

        Assert.assertEquals(errorExp, result)

        verify(loadHeroUseCase).execute(Unit)
    }
}