package com.fanhl.dreamnovel.ui.entrance

import android.os.Bundle
import com.fanhl.dreamnovel.R
import com.fanhl.dreamnovel.base.ARouters
import com.fanhl.dreamnovel.base.BaseActivity
import com.fanhl.dreamnovel.base.navigation
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        tv_name.postDelayed({
            ARouters.Account.LOGIN.navigation()
            finish()
        }, 2000)
    }
}
