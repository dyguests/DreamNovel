package com.fanhl.dreamnovel.square.module

import com.fanhl.dreamnovel.net.NetClient
import com.fanhl.dreamnovel.square.service.GithubService
import dagger.Module
import dagger.Provides

/**
 * 用来提供广场相关网络请求的Service
 */
@Module
class SquareServiceModule {
    @Provides
    internal fun provideGithubService(): GithubService = NetClient.get<GithubService>()
}