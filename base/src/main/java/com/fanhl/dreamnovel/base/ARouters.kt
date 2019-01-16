package com.fanhl.dreamnovel.base

import com.alibaba.android.arouter.launcher.ARouter

/**
 * 这里存放所有ARouter的跳转Path
 */
object ARouters {
    /**
     * module bizAccount
     */
    object Account {
        const val LOGIN = "/account/login"
    }

    object Home {
        const val MAIN = "/home/main"
    }

    object Writing {
        const val WRITING = "/writing/writing"
    }
}

fun String.navigation() {
    ARouter.getInstance().build(this).navigation()
}