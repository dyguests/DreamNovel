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

    private var displayWidth: Int = 0
    private var displayHeight: Int = 0

    /**
     *  绘制小说内容的画笔
     */
    private val titlePaint: TextPaint = TextPaint()

    /**
     *  绘制小说内容的画笔
     */
    private val contentPaint: TextPaint = TextPaint()

    //标题的行间距
    private var mTitleInterval: Int = 0
    //行间距
    private var mTextInterval: Int = 0

    //段落距离(基于行间距的额外距离)
    private var mTitlePara: Int = 0
    private var mTextPara: Int = 0

    var chapter: Chapter? = null
        set(value) {
            if (field == value) {
                return
            }

            field = value
            pagination(chapter)
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

        titlePaint.textSize = textSize * 18f / 14f
        titlePaint.color = textColor

        contentPaint.textSize = textSize
        contentPaint.color = textColor

        mTitleInterval = (titlePaint.textSize / 2).toInt()
        mTextInterval = (contentPaint.textSize / 2).toInt()

        mTitlePara = titlePaint.textSize.toInt()
        mTextPara = textSize.toInt()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        displayWidth = width - paddingLeft - paddingRight
        displayHeight = height - paddingTop - paddingBottom
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.RED)
    }

    /**
     * 分页
     *
     * 将 chapter 分成 pages
     */
    private fun pagination(chapter: Chapter?): ArrayList<Page>? {
        chapter ?: return null

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        val top: Float

        top = -contentPaint.fontMetrics.top

        var str: String? = null

        ////////////////////////////////////////////////////////////////////////////////////////////////////


        //这里存放所有分页
        val pages = arrayListOf<Page>()
        //分成分行
        val lines = arrayListOf<String>()
        var rHeight = displayHeight
        var titleLinesCount = 0
        //是否展示标题
        var showTitle = true
        //默认展示标题
        var paragraph = chapter.title ?: ""

        chapter.content ?: return pages

        //将内容分成行
        val strings = chapter.content.split("\n")

        var i = 0
        while (showTitle || i < strings.size) {
            if (!showTitle) {
                paragraph = strings[i]
            }

            i++

            // 重置段落
            if (!showTitle) {
                paragraph = paragraph.replace("\\s".toRegex(), "")
                // 如果只有换行符，那么就不执行
                if (paragraph == "") continue
//                paragraph = StringUtils.halfToFull("  $paragraph\n")//临时不需要这个逻辑
            } else {
                //设置 title 的顶部间距
                rHeight -= titlePaint.textSize.toInt()
            }


            var wordCount = 0
            var subStr: String? = null
            while (paragraph.isNotEmpty()) {
                //当前空间，是否容得下一行文字
                rHeight -= if (showTitle) {
                    titlePaint.textSize.toInt()
                } else {
                    contentPaint.textSize.toInt()
                }

                // 一页已经填充满了，创建 TextPage
                if (rHeight <= 0) {
                    //FIXME 等会写这里
                    //FIXME 等会写这里
                    //FIXME 等会写这里
                    //FIXME 等会写这里

//                    // 创建Page
//                    val page = TxtPage()
//                    page.position = pages.size
//                    page.title = StringUtils.convertCC(mContext, chapter.title, convertType)
//                    page.lines = ArrayList(lines)
//                    page.titleLines = titleLinesCount
//                    pages.add(page)
//                    // 重置Lines
//                    lines.clear()
//                    rHeight = mVisibleHeight
//                    titleLinesCount = 0

                    continue
                }

                //测量一行占用的字节数
                wordCount = (if (showTitle) {
                    titlePaint
                } else {
                    contentPaint
                }).breakText(paragraph, true, displayWidth.toFloat(), null)

                subStr = paragraph.substring(0, wordCount)
                if (subStr != "\n") {
                    //将一行字节，存储到lines中
                    lines.add(subStr)

                    //设置段落间距
                    if (showTitle) {
                        titleLinesCount += 1
                        rHeight -= mTitleInterval
                    } else {
                        rHeight -= mTextInterval
                    }
                }

                //裁剪
                paragraph = paragraph.substring(wordCount)
            }

            //增加段落的间距
            if (!showTitle && lines.size != 0) {
                rHeight = rHeight - mTextPara + mTextInterval
            }

            if (showTitle) {
                rHeight = rHeight - mTitlePara + mTitleInterval
                showTitle = false
            }
        }

        //sjj/novel/view/reader/page/PageLoader.java:1324

        return pages
    }
}