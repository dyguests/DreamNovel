package com.fanhl.dreamnovel.writing

import android.os.Bundle
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.fanhl.dreamnovel.base.ARouters
import com.fanhl.dreamnovel.base.BaseActivity
import com.fanhl.dreamnovel.base.util.DistinctLiveData
import com.fanhl.dreamnovel.base.util.lazyModel
import com.fanhl.dreamnovel.base.util.observe
import com.fanhl.dreamnovel.database.DbClient
import com.fanhl.dreamnovel.database.dao.writing.ArticleDao
import com.fanhl.dreamnovel.database.entity.writing.Article
import kotlinx.android.synthetic.main.activity_writing.*
import kotlinx.android.synthetic.main.content_writing.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.textChangedListener
import java.util.*

/**
 * 写日记、文章、。。。
 */
@Route(path = ARouters.Writing.WRITING)
class WritingActivity : BaseActivity() {

    private val viewModel by lazyModel<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        assignViews()
        initData()
    }

    private fun assignViews() {
        et_content.textChangedListener {
            onTextChanged { charSequence, start, before, count ->
                viewModel.content.value = charSequence?.toString()
            }
        }

        viewModel.apply {
            content.observe(this@WritingActivity) {
                et_content.setTextDistinct(it)
            }
        }
    }

    private fun initData() {
        viewModel.article.value = intent.getParcelableExtra("Article")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.saveCache()
    }

    class ViewModel : androidx.lifecycle.ViewModel() {
        /**
         * 当前文章
         *
         * 这是输入参数
         */
        val article = DistinctLiveData<Article>()

        // ------------------------------------------ form表单 start ------------------------------------------

        val content = DistinctLiveData<String>()

        // ------------------------------------------ form表单 end ------------------------------------------

        init {
            article.observeForever {
                content.value = it.content
            }
        }

        fun saveCache() {
            if (content.value.isNullOrEmpty()) {
                return
            }

            doAsync {
                DbClient.get<ArticleDao>().insertAll(
                    Article(
                        content = content.value
                    )
                )
            }
        }
    }
}

private fun TextView.setTextDistinct(text: String?) {
    if (!Objects.equals(getText()?.toString(), text)) {
        setText(text)
    }
}
