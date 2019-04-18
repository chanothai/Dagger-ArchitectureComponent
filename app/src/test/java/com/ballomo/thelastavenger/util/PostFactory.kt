package com.ballomo.thelastavenger.util

import com.ballomo.shared.data.entity.hero.Results
import java.util.concurrent.atomic.AtomicInteger

class PostFactory {
    private val counter = AtomicInteger(0)
    fun fakeResultPost(): Results {

        val id = counter.incrementAndGet()
        return Results(
            id = id,
            name = "name $id",
            status = "status $id",
            species = "species $id"
        )
    }
}