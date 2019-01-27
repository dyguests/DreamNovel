package com.fanhl.dreamnovel.bookshelf.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fanhl.dreamnovel.base.util.placeholder
import com.fanhl.dreamnovel.bookshelf.R
import com.fanhl.dreamnovel.database.entity.writing.Article
import com.fanhl.dreamnovel.database.entity.writing.Paragrafo
import kotlinx.android.synthetic.main.item_bookshelf_diary.view.*

class BookshelfAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_bookshelf_diary) {
    override fun convert(helper: BaseViewHolder?, item: Article?) {
        helper?.itemView?.apply {
            item?.title?.takeIf { it.isNotEmpty() }?.let {
                tv_title.visibility = View.VISIBLE
                tv_title.text = it
            } ?: let {
                tv_title.visibility = View.GONE
            }

            //第一个段落（用来显示的）
            val paragrafo = item?.content?.firstOrNull()
            when (paragrafo?.type) {
                Paragrafo.TYPE_TEXT -> {
                    tv_content.apply {
                        visibility = View.VISIBLE
                        text = paragrafo.content
                    }
                    img_cover.visibility = View.GONE
                }
                Paragrafo.TYPE_IMAGE -> {
                    tv_content.visibility = View.GONE
                    img_cover.apply {
                        visibility = View.VISIBLE
                        Glide.with(this)
                            .load(paragrafo.content)
                            .placeholder(R.drawable.cover_place_holder)
                            .into(this)
                    }
                }
                else -> {
                    tv_content.visibility = View.GONE
                    img_cover.visibility = View.GONE
                }
            }
        }
    }
}