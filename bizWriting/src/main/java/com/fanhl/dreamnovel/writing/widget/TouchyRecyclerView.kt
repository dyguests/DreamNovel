package com.fanhl.dreamnovel.writing.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * 一个点击RecyclerView空余区域可以回调的RecyclerView
 */
class TouchyRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    private var onNoChildClickListener: (() -> Unit)? = null

    fun setOnNoChildClickListener(listener: () -> Unit) {
        this.onNoChildClickListener = listener
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        // The findChildViewUnder() method returns null if the touch event
        // occurs outside of a child View.
        // Change the MotionEvent action as needed. Here we use ACTION_DOWN
        // as a simple, naive indication of a click.
        if (event.action == MotionEvent.ACTION_DOWN && findChildViewUnder(event.x, event.y) == null) {
            onNoChildClickListener?.invoke()
        }
        return super.dispatchTouchEvent(event)
    }
}