package com.fanhl.dreamnovel.writing.provider

import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.fanhl.dreamnovel.base.util.setTextDistinct
import com.fanhl.dreamnovel.database.entity.writing.Paragrafo
import com.fanhl.dreamnovel.writing.R
import com.fanhl.dreamnovel.writing.adapter.WritingAdapter
import kotlinx.android.synthetic.main.item_writing_text.view.*
import org.jetbrains.anko.sdk25.coroutines.textChangedListener

class TextProvider(
    private val onContentChanged: (position: Int, text: String?) -> Unit
) : BaseItemProvider<Paragrafo, BaseViewHolder>() {
    override fun layout() = R.layout.item_writing_text

    override fun viewType() = WritingAdapter.TYPE_TEXT

    override fun convert(helper: BaseViewHolder?, data: Paragrafo?, position: Int) {
        helper?.itemView?.apply {
            et_content.textChangedListener {
                onTextChanged { charSequence, start, before, count ->
                    this@TextProvider.onContentChanged(position, charSequence?.toString())
                }
            }

            et_content.setTextDistinct(data?.content)
        }
    }
}
