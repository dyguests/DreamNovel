package com.fanhl.dreamnovel.square.component

import com.fanhl.dreamnovel.square.module.SquareServiceModule
import com.fanhl.dreamnovel.square.service.GithubService
import com.fanhl.dreamnovel.square.service.SquareService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [ActivityComponent::class, ContainerComponent::class], modules = [SquareServiceModule::class])
interface SquareServiceComponent {
    //    fun inject(squareFragment: SquareFragment)

    fun githubService(): GithubService

    fun squareService(): SquareService
}