package com.fanhl.dreamnovel.square.component

import com.fanhl.dreamnovel.square.SquareFragment
import com.fanhl.dreamnovel.square.module.ContainerModule
import dagger.Component

@Component(dependencies = [ActivityComponent::class], modules = [ContainerModule::class])
interface ContainerComponent {
    fun inject(squareFragment: SquareFragment)
}