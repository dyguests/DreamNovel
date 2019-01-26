package com.fanhl.dreamnovel.writing

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.fanhl.dreamnovel.base.ARouters
import com.fanhl.dreamnovel.base.BaseActivity
import com.fanhl.dreamnovel.base.util.DistinctLiveData
import com.fanhl.dreamnovel.base.util.lazyModel
import com.fanhl.dreamnovel.base.util.observe
import com.fanhl.dreamnovel.base.util.setTextDistinct
import com.fanhl.dreamnovel.database.DbClient
import com.fanhl.dreamnovel.database.dao.writing.ArticleDao
import com.fanhl.dreamnovel.database.entity.writing.Article
import kotlinx.android.synthetic.main.activity_writing.*
import kotlinx.android.synthetic.main.content_writing.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.textChangedListener

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

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.saveCache()
    }

    private fun assignViews() {
        et_title.textChangedListener {
            onTextChanged { charSequence, start, before, count ->
                viewModel.title.value = charSequence?.toString()
            }
        }
        et_content.textChangedListener {
            onTextChanged { charSequence, start, before, count ->
                viewModel.content.value = charSequence?.toString()
            }
        }

        viewModel.apply {
            title.observe(this@WritingActivity) {
                et_title.setTextDistinct(it)
            }
            content.observe(this@WritingActivity) {
                et_content.setTextDistinct(it)
            }
        }
    }

    private fun initData() {
        viewModel.article.value = intent.getParcelableExtra("Article")
    }

    class ViewModel : androidx.lifecycle.ViewModel() {
        /**
         * 当前文章
         *
         * 这是输入参数
         */
        val article = DistinctLiveData<Article>()

        // ------------------------------------------ form表单 start ------------------------------------------

        val title = DistinctLiveData<String>()
        val content = DistinctLiveData<String>()

        // ------------------------------------------ form表单 end ------------------------------------------

        init {
            article.observeForever {
                title.value = it.title
                content.value = it.content
            }
        }

        fun saveCache() {
            if (article.value == null
                && title.value.isNullOrEmpty()
                && content.value.isNullOrEmpty()
            ) {
                return
            }

            doAsync {
                DbClient.get<ArticleDao>().apply {
                    article.value?.let {
                        update(it.apply {
                            title = this@ViewModel.title.value
                            content = this@ViewModel.content.value
                        })
                    } ?: insertAll(
                        Article(
                            title = title.value,
                            content = content.value
                        )
                    )
                }
            }
        }
    }
}
