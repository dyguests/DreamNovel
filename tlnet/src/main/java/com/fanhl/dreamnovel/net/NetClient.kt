package com.fanhl.dreamnovel.net

object NetClient {
    inline fun <reified T> get() = (IAppNet.instance ?: throw Exception("IAppNet not init")).get(T::class.java)
}