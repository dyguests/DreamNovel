package com.fanhl.dreamnovel.writing.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 一个点击RecyclerView空余区域可以回调的RecyclerView
 */
public class TouchyRecyclerView extends RecyclerView {
    public TouchyRecyclerView(@NonNull Context context) {
        super(context);
    }

    public TouchyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private OnNoChildClickListener listener;

    public interface OnNoChildClickListener {
        public void onNoChildClick();
    }

    public void setOnNoChildClickListener(OnNoChildClickListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // The findChildViewUnder() method returns null if the touch event
        // occurs outside of a child View.
        // Change the MotionEvent action as needed. Here we use ACTION_DOWN
        // as a simple, naive indication of a click.
        if (event.getAction() == MotionEvent.ACTION_DOWN
                && findChildViewUnder(event.getX(), event.getY()) == null) {
            if (listener != null) {
                listener.onNoChildClick();
            }
        }
        return super.dispatchTouchEvent(event);
    }
}