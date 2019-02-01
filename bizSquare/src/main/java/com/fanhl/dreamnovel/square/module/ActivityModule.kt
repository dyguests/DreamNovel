package com.fanhl.dreamnovel.square.module

import com.fanhl.dreamnovel.square.model.UserModel

@Deprecated("测试用")
//@Module
class ActivityModule {
//    @Provides
    fun provideUserModel(): UserModel {
        return UserModel()
    }
}