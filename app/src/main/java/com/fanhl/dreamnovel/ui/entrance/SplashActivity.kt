package com.fanhl.dreamnovel.ui.entrance

import android.os.Bundle
import com.fanhl.dreamnovel.R
import com.fanhl.dreamnovel.account.LoginActivity
import com.fanhl.dreamnovel.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        tv_name.postDelayed({
            LoginActivity.launch(this@SplashActivity)
            finish()
        }, 2000)
    }
}
