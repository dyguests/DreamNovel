package com.fanhl.widget.page

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat

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
            pagination()
        }

    /**
     * 分前章的具体分页
     */
    var pages = listOf<Page>()

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.ChapterView, defStyleAttr, R.style.Widget_Chapter_View)

        val textSize = a.getDimension(R.styleable.ChapterView_textSize, resources.getDimension(R.dimen.chapter_view_text_size_default))
        val textColor = a.getColor(R.styleable.ChapterView_textColor, ContextCompat.getColor(context, R.color.chapter_view_text_color_default))

        a.recycle()

        contentPaint.textSize = textSize
        contentPaint.color = textColor
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.RED)
    }

    /**
     * 分页
     */
    private fun pagination() {

    }
}