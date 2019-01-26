package com.fanhl.dreamnovel.writing.provider

import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.fanhl.dreamnovel.writing.R
import com.fanhl.dreamnovel.writing.adapter.WritingAdapter
import com.fanhl.dreamnovel.writing.model.WritingItem

class TextProvider : BaseItemProvider<WritingItem, BaseViewHolder>() {
    override fun layout() = R.layout.item_writing_text

    override fun viewType() = WritingAdapter.TYPE_TEXT

    override fun convert(helper: BaseViewHolder?, data: WritingItem?, position: Int) {

    }
}
