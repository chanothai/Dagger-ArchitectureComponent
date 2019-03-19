package com.ballomo.shared.domain.hero

import com.ballomo.shared.domain.hero.entity.HeroEntity
import com.ballomo.shared.domain.hero.entity.Results
import com.ballomo.shared.data.repository.HeroRepo
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.lang.NullPointerException

class LoadHeroUseCaseTest {

    @Mock
    lateinit var heroRepo: HeroRepo
    private lateinit var loadHeroUseCase: LoadHeroUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        loadHeroUseCase = LoadHeroUseCase(heroRepo)
    }

    @Test
    fun `load hero success`() {
        val repoResponse = HeroEntity(
            results = arrayListOf(
                Results(
                    name = "BallOMO",
                    image = "img2.jpg"
                ))
        )

        val expResponse = ListHeroInformation(
            arrayListOf(
                LoadHeroInformation(
                    repoResponse.results[0].name ?: "",
                    repoResponse.results[0].image ?: ""
                )
            )
        )

        `when`(heroRepo.getAll()).thenReturn(Observable.just(repoResponse))

        val resultActual = loadHeroUseCase.execute(Unit).test().values()[0]

        assertEquals(expResponse.heroInformation[0].name , resultActual.heroInformation[0].name)
        assertEquals(expResponse.heroInformation[0].image, resultActual.heroInformation[0].image)
        verify(heroRepo).getAll()
    }

    @Test
    fun `load hero incomplete then hero was null`() {
        val expError = NullPointerException()
        `when`(heroRepo.getAll()).thenReturn(Observable.error(expError))

        val result = loadHeroUseCase.execute(Unit).test().errors()[0]

        assertEquals(expError, result)
    }
}