package com.fanhl.dreamnovel.square.module

import com.fanhl.dreamnovel.square.model.ShoppingCartModel
import dagger.Module
import dagger.Provides


@Module
class ContainerModule {
    @Provides
    internal fun provideCartModel(): ShoppingCartModel {
        return ShoppingCartModel()
    }
}