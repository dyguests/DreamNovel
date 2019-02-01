package com.fanhl.dreamnovel.square.component

import com.fanhl.dreamnovel.square.model.UserModel
import com.fanhl.dreamnovel.square.module.ActivityModule
import dagger.Component


//@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    //    fun inject(fragment: SquareFragment)
    fun userModel(): UserModel
}
