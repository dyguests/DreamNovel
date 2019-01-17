package com.fanhl.dreamnovel.bookshelf.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fanhl.dreamnovel.bookshelf.R
import com.fanhl.dreamnovel.database.entity.writing.Article

class BookshelfAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_bookshelf_diary) {
    override fun convert(helper: BaseViewHolder?, item: Article?) {

    }
}