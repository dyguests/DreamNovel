package com.fanhl.dreamnovel.base.util

import androidx.fragment.app.Fragment
import org.jetbrains.anko.toast

fun Fragment.toast(message: Int) = activity?.toast(message)
fun Fragment.toast(message: CharSequence) = activity?.toast(message)