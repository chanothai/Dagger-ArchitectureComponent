package com.ballomo.thelastavenger.ui.hero

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ballomo.shared.result.EventObserver
import com.ballomo.shared.util.viewModelProvider
import com.ballomo.thelastavenger.R
import com.ballomo.thelastavenger.common.BaseActivity
import com.ballomo.thelastavenger.databinding.ActivityMainBinding
import com.ballomo.thelastavenger.util.UserPreference
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), LifecycleOwner {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var heroViewModel: HeroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        subscribeError()

        if (savedInstanceState == null) {
            heroViewModel.loadHeroInformation()
        }
    }

    private fun initDataBinding() {
        heroViewModel = viewModelProvider(viewModelFactory)

        val dataBinding:ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.postHero.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        dataBinding.viewModel = heroViewModel
    }

    private fun subscribeError() {
        heroViewModel.messageLiveData.observe(this, EventObserver {message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })
    }
}
