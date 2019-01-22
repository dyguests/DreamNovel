package com.fanhl.dreamnovel.net

interface IAppNet {
    fun <T> get(clazz: Class<T>): T

    companion object {
        var instance: IAppNet? = null
    }
}