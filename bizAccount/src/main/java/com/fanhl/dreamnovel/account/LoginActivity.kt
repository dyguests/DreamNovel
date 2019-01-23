package com.fanhl.dreamnovel.account

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.fanhl.dreamnovel.base.ARouters
import com.fanhl.dreamnovel.base.BaseActivity
import com.fanhl.dreamnovel.base.navigation
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick

@Route(path = ARouters.Account.LOGIN)
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setTranslucent(this)
        setContentView(R.layout.activity_login)

        btn_login.onClick {
            ARouters.Home.MAIN.navigation()
            finish()
        }
    }
}
