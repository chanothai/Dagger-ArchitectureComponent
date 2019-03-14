package com.ballomo.thelastavenger.ui.hero

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ballomo.shared.result.EventObserver
import com.ballomo.shared.util.viewModelProvider
import com.ballomo.thelastavenger.R
import com.ballomo.thelastavenger.common.BaseActivity
import com.ballomo.thelastavenger.util.UserPreference
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var userPreference: UserPreference

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var heroViewModel: HeroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heroViewModel = viewModelProvider(viewModelFactory)

        heroViewModel.messageLiveData.observe(this, EventObserver {message ->

            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })

        testInjectDagger()
    }

    private fun testInjectDagger() {
        userPreference.saveUserId(26)
        userPreference.saveUserName("Chanothai Duanghrahwa")

        btn_show_ms.setOnClickListener {
            heroViewModel.loadHeroInformation()
        }
    }
}
