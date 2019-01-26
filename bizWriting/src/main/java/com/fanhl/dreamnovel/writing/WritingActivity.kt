package com.fanhl.dreamnovel.writing

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import com.fanhl.dreamnovel.image.ImagePickerApi
import com.fanhl.dreamnovel.writing.adapter.WritingAdapter
import kotlinx.android.synthetic.main.activity_writing.*
import kotlinx.android.synthetic.main.content_writing.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.textChangedListener

/**
 * 写日记、文章、。。。
 */
@Route(path = ARouters.Writing.WRITING)
class WritingActivity : BaseActivity() {

    /** contents adapter */
    private val adapter by lazy { WritingAdapter() }

    private val viewModel by lazyModel<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        assignViews()
        initData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.writing, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.action_add -> {
            ImagePickerApi.pick(this@WritingActivity)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.saveCache()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePickerApi.shouldHandle(requestCode, resultCode, data)) {
            val images = ImagePickerApi.getImages(data)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun assignViews() {
        et_title.textChangedListener {
            onTextChanged { charSequence, start, before, count ->
                viewModel.title.value = charSequence?.toString()
            }
        }

        recycler_view.adapter = adapter

//        et_content.textChangedListener {
//            onTextChanged { charSequence, start, before, count ->
//                viewModel.content.value = charSequence?.toString()
//            }
//        }

        viewModel.apply {
            title.observe(this@WritingActivity) {
                et_title.setTextDistinct(it)
            }
//            content.observe(this@WritingActivity) {
//                et_content.setTextDistinct(it)
//            }
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
