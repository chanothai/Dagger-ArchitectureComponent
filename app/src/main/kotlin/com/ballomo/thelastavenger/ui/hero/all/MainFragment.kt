package com.ballomo.thelastavenger.ui.hero.all


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ballomo.shared.data.HeroAdapter
import com.ballomo.thelastavenger.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val heroViewModel: HeroViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        heroViewModel.loadHeroResult.observe(this, Observer {
            heroViewModel.postHeroListAdapter.updateListHeroInformation(it)
        })
    }
}
