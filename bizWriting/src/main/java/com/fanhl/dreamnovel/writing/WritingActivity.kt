package com.fanhl.dreamnovel.writing

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.annotation.Route
import com.fanhl.dreamnovel.base.ARouters
import com.fanhl.dreamnovel.base.BaseActivity
import com.fanhl.dreamnovel.base.util.lazyModel
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
    }

    private fun assignViews() {
        et_content.textChangedListener {
            onTextChanged { charSequence, start, before, count ->
                viewModel.content.value = charSequence?.toString()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.saveCache()
    }

    class ViewModel : androidx.lifecycle.ViewModel() {
        val content = MutableLiveData<String>()

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
