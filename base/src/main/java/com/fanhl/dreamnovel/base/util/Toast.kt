package com.fanhl.dreamnovel.base.util

import androidx.fragment.app.Fragment
import org.jetbrains.anko.toast

inline fun Fragment.toast(message: Int) = activity?.toast(message)
inline fun Fragment.toast(message: CharSequence) = activity?.toast(message)