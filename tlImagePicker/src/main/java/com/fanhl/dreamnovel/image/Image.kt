package com.fanhl.dreamnovel.image

/**
 * 自定义的Image数据结构
 *
 * 防止迭代依赖关系
 */
data class Image(
    var id: Long = 0,
    var name: String? = null,
    var path: String? = null
)