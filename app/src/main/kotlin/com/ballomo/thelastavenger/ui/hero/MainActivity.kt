package com.ballomo.thelastavenger.ui.hero

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.ballomo.shared.util.viewModelProvider
import com.ballomo.thelastavenger.R
import com.ballomo.thelastavenger.common.BaseActivity
import com.ballomo.thelastavenger.ui.hero.all.HeroViewModel
import com.ballomo.thelastavenger.ui.hero.paging.HeroPagingViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
