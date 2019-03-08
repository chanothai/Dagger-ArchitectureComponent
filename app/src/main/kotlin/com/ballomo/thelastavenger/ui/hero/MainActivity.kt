package com.ballomo.thelastavenger.ui.hero

import android.os.Bundle
import android.widget.Toast
import com.ballomo.thelastavenger.R
import com.ballomo.thelastavenger.common.BaseActivity
import com.ballomo.thelastavenger.util.UserPreference
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userPreference.saveUserId(26)
        userPreference.saveUserName("Chanothai Duanghrahwa")

        btn_show_ms.setOnClickListener {
            Toast.makeText(this, "${userPreference.getUserID()} , ${userPreference.getUserName()}", Toast.LENGTH_LONG).show()
        }
    }
}
