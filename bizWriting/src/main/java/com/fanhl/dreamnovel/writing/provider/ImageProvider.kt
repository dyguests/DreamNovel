package com.fanhl.dreamnovel.writing.provider

import com.bumptech.glide.Glide
import com.fanhl.dreamnovel.base.util.placeholder
import com.fanhl.dreamnovel.database.entity.writing.Paragrafo
import com.fanhl.dreamnovel.writing.R
import com.fanhl.dreamnovel.writing.adapter.WritingAdapter
import com.fanhl.dreamnovel.writing.helper.WritingViewHolder
import kotlinx.android.synthetic.main.item_writing_image.view.*

class ImageProvider(
    private val onContentChanged: (position: Int, text: String?) -> Unit
) : WriteProvider() {
    override fun layout() = R.layout.item_writing_image

    override fun viewType() = WritingAdapter.TYPE_IMAGE

    override fun convert(helper: WritingViewHolder?, data: Paragrafo?, position: Int) {
        helper?.itemView?.apply {
            Glide.with(img_cover)
                .load(data?.content)
                .placeholder(R.drawable.cover_place_holder)
                .into(img_cover)
        }
    }
}
