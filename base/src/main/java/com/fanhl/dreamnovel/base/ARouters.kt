package com.fanhl.dreamnovel.base

import android.os.Parcelable
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

fun String.navigation(vararg extras: Pair<String, Any?>) {
    ARouter.getInstance()
        .build(this).apply {
            extras.forEach {
                if (it.second is Parcelable) {
                    withParcelable(it.first, it.second as Parcelable)
                }
            }
        }
        .navigation()
}