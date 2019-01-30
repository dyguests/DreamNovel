package com.fanhl.dreamnovel.square.module

import com.fanhl.dreamnovel.square.model.UserModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {
    @Provides
    fun provideUserModel(): UserModel {
        return UserModel()
    }
}