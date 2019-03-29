package com.ballomo.thelastavenger.ui.hero.all


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ballomo.shared.util.viewModelProvider
import com.ballomo.thelastavenger.common.BaseFragment
import com.ballomo.thelastavenger.databinding.FragmentMainBinding
import com.ballomo.thelastavenger.ui.hero.MainActivity
import com.ballomo.thelastavenger.ui.hero.model.ListHeroInformation

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var heroViewModel: HeroViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = (activity as MainActivity).viewModelFactory
        heroViewModel = viewModelProvider(factory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = heroViewModel

        heroViewModel.loadHeroInformation()
        observeHeroInformation()
    }

    private fun observeHeroInformation() {
//        heroViewModel.loadHeroResult.observe(this, Observer {
//            when(it) {
//                is ListHeroInformation -> {
//                    binding.viewModel?.loadingVisibility?.value = View.GONE
//                    binding.viewModel?.postHeroListAdapter?.updateListHeroInformation(it)
//                }
//                is Boolean -> {
//                    binding.viewModel?.loadingVisibility?.value = View.VISIBLE
//                }
//            }
//        })
    }
}