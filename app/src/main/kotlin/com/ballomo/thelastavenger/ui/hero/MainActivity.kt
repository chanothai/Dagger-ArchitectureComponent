package com.ballomo.thelastavenger.ui.hero

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.ballomo.shared.util.viewModelProvider
import com.ballomo.thelastavenger.R
import com.ballomo.thelastavenger.common.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), LifecycleOwner {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var heroViewModel: HeroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDataBinding()
    }

    private fun initDataBinding() {
        heroViewModel = viewModelProvider(viewModelFactory)
    }
}
