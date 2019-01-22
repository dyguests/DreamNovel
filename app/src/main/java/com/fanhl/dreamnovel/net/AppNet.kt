package com.fanhl.dreamnovel.net

import android.content.Context
import android.util.Log

/**
 * 网络接口
 */
class AppNet(private val context: Context) : IAppNet {
    override fun <T> get(clazz: Class<T>): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        /** TAG  */
        private val TAG = AppNet::class.java.simpleName

        fun init(context: Context) {
            if (IAppNet.instance != null) {
                Log.d(TAG, "AppNet already inited")
                return
            }

            IAppNet.instance = AppNet(context)
        }
    }
}