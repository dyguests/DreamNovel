package com.fanhl.dreamnovel.writing.helper

import android.view.View
import com.chad.library.adapter.base.BaseViewHolder

class WritingViewHolder(view: View) : BaseViewHolder(view) {
    var onFocus: (() -> Unit)? = null
    /**
     * 这里实现当前ViewHolder获取焦点的逻辑
     *
     * RecylcerView点击空白处后，最后一个WritingViewHolder获取焦点的逻辑
     */
    fun requestFocus() {
        onFocus?.invoke()
    }
}