package com.fanhl.dreamnovel.base.util

import androidx.annotation.DrawableRes
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions

fun <TranscodeType> RequestBuilder<TranscodeType>.placeholder(@DrawableRes resourceId: Int) = apply(RequestOptions().placeholder(resourceId))