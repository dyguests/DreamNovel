package com.fanhl.dreamnovel

import android.app.Application
import com.fanhl.dreamnovel.io.ZhuishuClient

class App : Application() {
    val zhuishuClient by lazy { ZhuishuClient() }
}
