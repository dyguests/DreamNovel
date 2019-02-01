package com.fanhl.dreamnovel.square.provider

import android.view.View
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.fanhl.dreamnovel.square.R
import com.fanhl.dreamnovel.square.adapter.SquareAdapter
import kotlinx.android.synthetic.main.item_square_recommend.view.*

/**
 * 广场adapter 的 推荐provider
 */
class RecommendProvider : BaseItemProvider<Recommend, BaseViewHolder>() {
    override fun layout() = R.layout.item_square_recommend

    override fun viewType() = SquareAdapter.TYPE_RECOMMEND

    override fun convert(helper: BaseViewHolder?, data: Recommend?, position: Int) {
        helper?.itemView?.apply {
            data?.title?.takeIf { it.isNotEmpty() }?.let {
                tv_title.visibility = View.VISIBLE
                tv_title.text = it
            } ?: let {
                tv_title.visibility = View.GONE
            }
            data?.description?.takeIf { it.isNotEmpty() }?.let {
                tv_description.visibility = View.VISIBLE
                tv_description.text = it
            } ?: let {
                tv_description.visibility = View.GONE
            }
        }
    }
}

data class Recommend(
    var title: String? = null,
    var description: String? = null
)