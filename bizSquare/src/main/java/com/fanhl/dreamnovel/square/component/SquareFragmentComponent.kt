package com.fanhl.dreamnovel.square.component

import com.fanhl.dreamnovel.square.SquareFragment
import com.fanhl.dreamnovel.square.util.ActivityScope
import dagger.Component

/**
 * SquareFragment的注入器
 */
@ActivityScope
@Component(dependencies = [SquareServiceComponent::class])
interface SquareFragmentComponent {
    fun inject(squareFragment: SquareFragment)
}