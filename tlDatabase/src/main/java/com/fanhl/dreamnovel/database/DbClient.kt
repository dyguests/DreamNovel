package com.fanhl.dreamnovel.database

object DbClient {
    inline fun <reified T> get() = (IAppDatabase.instance ?: throw Exception("IAppDatabase not init")).get(T::class.java)
}

