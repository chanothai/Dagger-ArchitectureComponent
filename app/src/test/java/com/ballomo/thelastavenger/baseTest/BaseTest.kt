package com.ballomo.thelastavenger.baseTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.rules.TestRule

open class BaseTest {
    @get:Rule
    var rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
}