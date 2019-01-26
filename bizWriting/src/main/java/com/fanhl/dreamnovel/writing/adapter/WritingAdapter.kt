package com.fanhl.dreamnovel.writing.adapter

import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.MultipleItemRvAdapter
import com.fanhl.dreamnovel.database.entity.writing.Paragrafo
import com.fanhl.dreamnovel.writing.provider.ImageProvider
import com.fanhl.dreamnovel.writing.provider.TextProvider

class WritingAdapter(
    private val onContentChanged: (position: Int, content: String?) -> Unit
) : MultipleItemRvAdapter<Paragrafo, BaseViewHolder>(null) {
    init {
        finishInitialize()
    }

    override fun registerItemProvider() {
        mProviderDelegate.registerProvider(TextProvider(onContentChanged))
        mProviderDelegate.registerProvider(ImageProvider(onContentChanged))
    }

    override fun getViewType(item: Paragrafo?) = when (item?.type) {
        Paragrafo.TYPE_TEXT -> TYPE_TEXT
        Paragrafo.TYPE_IMAGE -> TYPE_IMAGE
        else -> TYPE_TEXT
    }

    companion object {
        const val TYPE_TEXT = 1
        const val TYPE_IMAGE = 2
    }
}
