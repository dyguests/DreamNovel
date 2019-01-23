package com.fanhl.dreamnovel.square.adapter

import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.MultipleItemRvAdapter
import com.fanhl.dreamnovel.square.provider.RecommendProvider

class SquareAdapter : MultipleItemRvAdapter<Any, BaseViewHolder>(null) {

    init {
        finishInitialize()
    }

    override fun registerItemProvider() {
        mProviderDelegate.registerProvider(RecommendProvider())
    }


    override fun getViewType(entry: Any?): Int {
        return TYPE_RECOMMEND
    }

    companion object {
        const val TYPE_RECOMMEND = 1
    }
}
