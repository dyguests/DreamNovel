package com.fanhl.dreamnovel.database

interface IAppDatabase {
    fun <T> get(clazz: Class<T>): T?

    companion object {
       lateinit var INSTANCE: IAppDatabase
    }
}
