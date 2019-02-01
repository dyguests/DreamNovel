package com.fanhl.dreamnovel.square.module

import com.fanhl.dreamnovel.net.NetClient
import com.fanhl.dreamnovel.square.service.GithubService
import com.fanhl.dreamnovel.square.service.SquareService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * 用来提供广场相关网络请求的Service
 */
@Module
class SquareServiceModule {
    @Provides
    @Singleton
    internal fun provideGithubService() = NetClient.get<GithubService>()

    @Provides
    @Singleton
    internal fun provideSquareService() = NetClient.get<SquareService>()
}