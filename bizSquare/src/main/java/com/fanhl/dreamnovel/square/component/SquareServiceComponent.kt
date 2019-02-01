package com.fanhl.dreamnovel.square.component

import com.fanhl.dreamnovel.square.SquareFragment
import com.fanhl.dreamnovel.square.module.SquareServiceModule
import dagger.Component

@Component(dependencies = [ContainerComponent::class], modules = [SquareServiceModule::class])
interface SquareServiceComponent {
    fun inject(squareFragment: SquareFragment)
}