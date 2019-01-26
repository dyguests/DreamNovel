package com.fanhl.dreamnovel.bookshelf.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fanhl.dreamnovel.bookshelf.R
import com.fanhl.dreamnovel.database.entity.writing.Article
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
            //FIXME 之后要改的
            tv_content.text = item?.content?.firstOrNull()?.content
        }
    }
}