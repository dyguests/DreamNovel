package com.fanhl.widget.page

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable

/**
 * 章节View
 */
class ChapterView(
    context: Context, @Nullable attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    /**
     *  绘制小说内容的画笔
     */
    private val contentPaint: TextPaint = TextPaint()

    var chapter: Chapter? = null
        set(value) {
            if (field == value) {
                return
            }

            field = value

        }

    /**
     * 分前章的具体分页
     */
    var pages = listOf<Page>()

    init {

    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.RED)
    }
}