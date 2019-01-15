package com.fanhl.dreamnovel.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fanhl.dreamnovel.base.BaseActivity
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setTranslucent(this)
        setContentView(R.layout.activity_login)

        btn_login.onClick {

        }
    }

    companion object {
        /**
         * 启动LoginActivity
         *
         * @param context 上下文
         */
        fun launch(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}
