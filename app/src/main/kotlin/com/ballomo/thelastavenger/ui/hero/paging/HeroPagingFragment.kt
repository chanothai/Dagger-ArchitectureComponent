package com.ballomo.thelastavenger.ui.hero.paging


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ballomo.thelastavenger.R
import com.ballomo.thelastavenger.common.BaseFragment
import com.ballomo.thelastavenger.domain.InputLoadHero
import kotlinx.android.synthetic.main.fragment_hero_paging.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroPagingFragment : BaseFragment() {

    private val heroPagingViewModel: HeroPagingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hero_paging, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        // page size is it will loading later when scroll item to (itemAll - N) // 1 is N
        if (savedInstanceState == null) {
            heroPagingViewModel.requestHeroPaging(InputLoadHero(1))
        }
    }

    private fun initAdapter() {
        val adapter = PostHeroPageListAdapter {
            heroPagingViewModel.retryLoadHero()
        }

        recyclerView_paging.adapter = adapter

        heroPagingViewModel.loadHeroPagedListResult.observe(this, Observer {
            adapter.submitList(it)
        })

        heroPagingViewModel.networkState.observe(this, Observer {
            adapter.setNetworkState(it)
        })
    }
}
