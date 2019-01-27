package com.fanhl.dreamnovel.base.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * 显示软键盘
 */
fun View.showSoftInput() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}
