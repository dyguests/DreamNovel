package com.fanhl.dreamnovel.square.component

import com.fanhl.dreamnovel.square.SquareFragment

//@Component(dependencies = [ActivityComponent::class], modules = [ContainerModule::class])
interface ContainerComponent {
    fun inject(squareFragment: SquareFragment)
}