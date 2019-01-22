package com.fanhl.dreamnovel.square.provider

import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.fanhl.dreamnovel.square.R
import com.fanhl.dreamnovel.square.adapter.SquareAdapter

class RecommendProvider : BaseItemProvider<Recommend, BaseViewHolder>() {
    override fun layout() = R.layout.item_square_recommend

    override fun viewType() = SquareAdapter.TYPE_RECOMMEND

    override fun convert(helper: BaseViewHolder?, data: Recommend?, position: Int) {

    }

}

class Recommend