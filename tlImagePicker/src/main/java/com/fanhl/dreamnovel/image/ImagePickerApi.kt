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
    fun getImages(data: Intent?) = ImagePicker.getImages(data) ?: null

    @CheckResult
    fun getFirstImageOrNull(data: Intent?) = ImagePicker.getFirstImageOrNull(data) ?: null
}