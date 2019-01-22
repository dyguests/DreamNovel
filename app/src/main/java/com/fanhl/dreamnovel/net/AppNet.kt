package com.fanhl.dreamnovel.net

import android.content.Context
import android.util.Log
import com.fanhl.dreamnovel.square.service.SquareService

/**
 * 网络接口
 */
class AppNet(private val context: Context) : AbstractAppNet() {
    private val squareService by lazy { retrofit.create(SquareService::class.java) }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(clazz: Class<T>): T {
        if (clazz.isAssignableFrom(SquareService::class.java)) {
            return squareService as T
        }
        throw Exception("未找到对应 service")
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