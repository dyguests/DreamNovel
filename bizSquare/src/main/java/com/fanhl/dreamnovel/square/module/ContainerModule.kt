package com.fanhl.dreamnovel.square.module

import com.fanhl.dreamnovel.square.model.ShoppingCartModel
import dagger.Module
import dagger.Provides

@Deprecated("测试用")
@Module
class ContainerModule {
    @Provides
    internal fun provideCartModel(): ShoppingCartModel {
        return ShoppingCartModel()
    }
}