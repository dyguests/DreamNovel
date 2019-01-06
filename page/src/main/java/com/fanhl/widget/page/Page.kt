package com.fanhl.widget.page

/**
 * 一页数据
 *
 * @param titleLines 当前 lines 中为 title 的行数。
 */
data class Page(
    var position: Int = 0,
    var title: String? = null,
    var titleLines: Int = 0,
    var lines: List<String>? = null
)