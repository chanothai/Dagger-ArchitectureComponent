package com.ballomo.thelastavenger.ui.hero

import com.ballomo.thelastavenger.baseTest.BaseTest
import com.ballomo.thelastavenger.domain.LoadHeroUseCase
import com.ballomo.thelastavenger.ui.hero.all.HeroViewModel
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HeroViewModelTest: BaseTest() {

    @Mock
    lateinit var loadHeroUseCase: LoadHeroUseCase

    private lateinit var heroViewModel: HeroViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        heroViewModel = HeroViewModel(loadHeroUseCase)
    }
}