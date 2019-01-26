package com.fanhl.dreamnovel.writing.adapter

import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.MultipleItemRvAdapter
import com.fanhl.dreamnovel.database.entity.writing.Paragrafo
import com.fanhl.dreamnovel.writing.provider.TextProvider

class WritingAdapter(
    private val onContentChanged: (position: Int, content: String?) -> Unit
) : MultipleItemRvAdapter<Paragrafo, BaseViewHolder>(null) {
    init {
        finishInitialize()
    }

    override fun registerItemProvider() {
        mProviderDelegate.registerProvider(TextProvider(onContentChanged))
    }

    override fun getViewType(item: Paragrafo?) = if (item is Paragrafo) {
        TYPE_TEXT
    } else {
        TYPE_TEXT
    }

    companion object {
        const val TYPE_TEXT = 1
    }
}
