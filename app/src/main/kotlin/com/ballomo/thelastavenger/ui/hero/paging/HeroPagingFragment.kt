package com.ballomo.thelastavenger.ui.hero.paging


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ballomo.shared.util.viewModelProvider
import com.ballomo.thelastavenger.R
import com.ballomo.thelastavenger.common.BaseFragment
import com.ballomo.thelastavenger.domain.InputLoadHero
import com.ballomo.thelastavenger.ui.hero.MainActivity
import kotlinx.android.synthetic.main.fragment_hero_paging.*
import javax.inject.Inject

class HeroPagingFragment : BaseFragment() {

    @Inject lateinit var heroPagingViewModel: HeroPagingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = (activity as MainActivity).viewModelFactory
        heroPagingViewModel = viewModelProvider(factory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hero_paging, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        // page size is it will loading later when scroll item to (itemAll - N) // 1 is N
        heroPagingViewModel.requestHeroPaging(InputLoadHero(1))
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
