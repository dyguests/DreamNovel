package com.fanhl.dreamnovel.io

import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ZhuishuClient {
    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    companion object {
        const val BASE_URL = "http://api.zhuishushenqi.com/"
    }
}

/**
 * 追书神器Api
 *
 * https://juejin.im/entry/593a3fdf61ff4b006c737ca4
 */
interface ZhuishuService {
    /**
     * 获取所有分类
     */
    @GET("/cats/lv2/statistics")
    fun getStatistics(): Flowable<Any>
}