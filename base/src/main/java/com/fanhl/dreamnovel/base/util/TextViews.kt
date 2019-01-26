package com.fanhl.dreamnovel.base.util

import android.widget.TextView
import java.util.*

/**
 * 当内容一样时，不走setText
 */
fun TextView.setTextDistinct(text: String?) {
    if (!Objects.equals(getText()?.toString(), text)) {
        setText(text)
    }
}
