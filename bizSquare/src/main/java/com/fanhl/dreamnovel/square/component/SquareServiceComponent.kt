package com.fanhl.dreamnovel.square.component

import com.fanhl.dreamnovel.square.SquareFragment
import com.fanhl.dreamnovel.square.module.SquareServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [ActivityComponent::class, ContainerComponent::class], modules = [SquareServiceModule::class])
interface SquareServiceComponent {
    fun inject(squareFragment: SquareFragment)
}