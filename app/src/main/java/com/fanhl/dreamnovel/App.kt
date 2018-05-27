package com.fanhl.dreamnovel

import android.app.Application
import com.fanhl.dreamnovel.io.DreamClient

class App : Application() {
    val client by lazy { DreamClient() }
}
