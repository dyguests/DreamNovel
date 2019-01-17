package com.fanhl.dreamnovel

import com.alibaba.android.arouter.launcher.ARouter
import com.fanhl.dreamnovel.base.BaseApp
import com.fanhl.dreamnovel.database.AppDatabase
import org.jetbrains.anko.doAsync

class App : BaseApp() {

    override fun onCreate() {
        super.onCreate()

        initDb()
        initARouter()
    }

    private fun initDb() {
        doAsync {
            AppDatabase.init(this@App)
        }
    }

    private fun initARouter() {
        doAsync {
            //        // 这两行必须写在init之前，否则这些配置在init过程中将无效
            //        if (isDebug()) {
            // 打印日志
            ARouter.openLog()
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
            //        }
            // 尽可能早，推荐在Application中初始化
            ARouter.init(this@App)
        }
    }
}
