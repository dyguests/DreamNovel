package com.fanhl.dreamnovel.image

import android.app.Activity
import android.content.Intent
import androidx.annotation.CheckResult
import com.esafirm.imagepicker.features.ImagePicker

object ImagePickerApi {
    fun pick(activity: Activity) = ImagePicker.create(activity).start()
    @CheckResult
    fun shouldHandle(requestCode: Int, resultCode: Int, data: Intent?) = ImagePicker.shouldHandle(requestCode, resultCode, data)

    @CheckResult
    fun getImages(data: Intent?) = ImagePicker.getImages(data)?.map { it.toImage() }

    @CheckResult
    fun getFirstImageOrNull(data: Intent?) = ImagePicker.getFirstImageOrNull(data)?.toImage()

    /**
     * 自定义的Image数据结构
     *
     * 防止迭代依赖关系
     */
    private fun com.esafirm.imagepicker.model.Image.toImage() = Image(
        id = this.id,
        name = this.name,
        path = this.path
    )
}