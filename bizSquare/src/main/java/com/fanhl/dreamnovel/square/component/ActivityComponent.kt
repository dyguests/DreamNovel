package com.fanhl.dreamnovel.square.component

import com.fanhl.dreamnovel.square.SquareFragment
import com.fanhl.dreamnovel.square.module.ActivityModule
import dagger.Component


@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(fragment: SquareFragment)
}
