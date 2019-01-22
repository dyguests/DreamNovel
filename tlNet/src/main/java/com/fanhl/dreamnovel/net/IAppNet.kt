package com.fanhl.dreamnovel.net

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


interface IAppNet {

    fun <T> get(clazz: Class<T>): T

    companion object {
        var instance: IAppNet? = null
    }
}

abstract class AbstractAppNet : IAppNet {
    protected val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    companion object {
        /** 本机IP */
        const val BASE_URL = "http://192.168.0.103:8080"
    }
}