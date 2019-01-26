package com.fanhl.dreamnovel.writing.adapter

import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.MultipleItemRvAdapter
import com.fanhl.dreamnovel.writing.model.TextWritingItem
import com.fanhl.dreamnovel.writing.model.WritingItem
import com.fanhl.dreamnovel.writing.provider.TextProvider

class WritingAdapter : MultipleItemRvAdapter<WritingItem, BaseViewHolder>(null) {
    init {
        finishInitialize()
    }

    override fun registerItemProvider() {
        mProviderDelegate.registerProvider(TextProvider())
    }

    override fun getViewType(item: WritingItem?) = if (item is TextWritingItem) {
        TYPE_TEXT
    } else {
        TYPE_TEXT
    }

    companion object {
        const val TYPE_TEXT = 1
    }
}
