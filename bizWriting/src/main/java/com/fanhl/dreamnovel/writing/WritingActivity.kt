package com.fanhl.dreamnovel.writing

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.alibaba.android.arouter.facade.annotation.Route
import com.fanhl.dreamnovel.base.ARouters
import com.fanhl.dreamnovel.base.BaseActivity
import com.fanhl.dreamnovel.base.util.*
import com.fanhl.dreamnovel.database.DbClient
import com.fanhl.dreamnovel.database.dao.writing.ArticleDao
import com.fanhl.dreamnovel.database.dao.writing.ParagrafoDao
import com.fanhl.dreamnovel.database.dao.writing.queryContent
import com.fanhl.dreamnovel.database.entity.writing.Article
import com.fanhl.dreamnovel.database.entity.writing.Paragrafo
import com.fanhl.dreamnovel.image.Image
import com.fanhl.dreamnovel.image.ImagePickerApi
import com.fanhl.dreamnovel.writing.adapter.WritingAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
    private val adapter by lazy {
        WritingAdapter { position, content ->
            viewModel.setContentParagrafo(position, content)
        }
    }

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
        viewModel.saveIntoDb()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePickerApi.shouldHandle(requestCode, resultCode, data)) {
            val images = ImagePickerApi.getImages(data)
            viewModel.addImages(images)
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

        viewModel.apply {
            title.observe(this@WritingActivity) {
                et_title.setTextDistinct(it)
            }
            content.observe(this@WritingActivity) {
                adapter.setNewData(it)
            }
        }
    }

    private fun initData() {
        recycler_view.adapter = adapter

        viewModel.article.value = intent.getParcelableExtra("Article") ?: Article()
    }

    class ViewModel : BaseViewModel() {
        /**
         * 当前文章
         *
         * 这是输入参数
         */
        val article = DistinctLiveData<Article>()

        // ------------------------------------------ form表单 start ------------------------------------------

        val title = DistinctLiveData<String>()
        val content = DistinctLiveData<MutableList<Paragrafo>>()

        // ------------------------------------------ form表单 end ------------------------------------------

        init {
            article.observeForever {
                title.value = it.title

                removeDispose(DISPOSABLE_CONTENT)
                it.queryContent()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeByNext {
                        content.value = it.toMutableList().apply {
                            if (isEmpty()) {
                                add(Paragrafo())
                            }
                        }
                    }
                    .autoDispose(DISPOSABLE_CONTENT)
            }
        }

        fun setContentParagrafo(index: Int, content: String?) {
            this@ViewModel.content.value?.getOrNull(index)?.content = content
        }

        fun addImages(images: List<Image>?) {
            if (images?.isNotEmpty() != true) {
                return
            }

            content.value = (content.value ?: mutableListOf()).apply {
                if (lastOrNull()?.isInitState() == true) {
                    removeAt(size - 1)
                    addAll(images.map {
                        Paragrafo(
                            type = Paragrafo.TYPE_IMAGE,
                            content = it.path
                        )
                    })
                }
                add(Paragrafo())
            }
        }

        fun saveIntoDb() {
            if (title.value.isNullOrBlank()
                && content.value?.getOrNull(0)?.isInitState() == true
            ) {
                return
            }

            doAsync {
                val ids = DbClient.get<ArticleDao>().insertAll(
                    article.value?.apply {
                        title = this@ViewModel.title.value
                        content = this@ViewModel.content.value
                    } ?: return@doAsync
                )
                //FIXME 如果最后一项为空则不加入（默认输入项）
                DbClient.get<ParagrafoDao>().insertAll(
                    *content.value?.onEach {
                        it.articleId = ids.firstOrNull() ?: return@doAsync
                    }?.toTypedArray() ?: return@doAsync
                )
            }
        }

        companion object {
            const val DISPOSABLE_CONTENT = "DISPOSABLE_CONTENT"
        }
    }
}

