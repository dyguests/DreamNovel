package com.fanhl.dreamnovel.writing.provider

import com.fanhl.dreamnovel.base.util.setTextDistinct
import com.fanhl.dreamnovel.base.util.showSoftInput
import com.fanhl.dreamnovel.database.entity.writing.Paragrafo
import com.fanhl.dreamnovel.writing.R
import com.fanhl.dreamnovel.writing.adapter.WritingAdapter
import com.fanhl.dreamnovel.writing.helper.WritingViewHolder
import kotlinx.android.synthetic.main.item_writing_text.view.*
import org.jetbrains.anko.sdk25.coroutines.textChangedListener


class TextProvider(
    private val onContentChanged: (position: Int, text: String?) -> Unit
) : WriteProvider() {
    override fun layout() = R.layout.item_writing_text

    override fun viewType() = WritingAdapter.TYPE_TEXT

    override fun convert(helper: WritingViewHolder?, data: Paragrafo?, position: Int) {
        helper?.itemView?.apply {
            helper.onFocus = {
                et_content.requestFocus()
                et_content.setSelection(et_content.length())
                et_content.showSoftInput()
            }
            et_content.apply {
                hint = if (position == 0) resources.getString(R.string.writing_content_hint) else null
                textChangedListener {
                    onTextChanged { charSequence, start, before, count ->
                        this@TextProvider.onContentChanged(position, charSequence?.toString())
                    }
                }

                setTextDistinct(data?.content)
            }
        }
    }
}
