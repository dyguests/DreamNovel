package com.fanhl.dreamnovel.bizwriting

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.annotation.Route
import com.fanhl.dreamnovel.base.ARouters
import com.fanhl.dreamnovel.base.BaseActivity
import com.fanhl.dreamnovel.base.util.getModel
import com.fanhl.dreamnovel.database.dao.bizwriting.ArticleDao
import com.fanhl.dreamnovel.database.entity.writing.Article
import com.fanhl.dreamnovel.database.RoomClient
import kotlinx.android.synthetic.main.activity_writing.*
import org.jetbrains.anko.doAsync

/**
 * 写日记、文章、。。。
 */
@Route(path = ARouters.Writing.WRITING)
class WritingActivity : BaseActivity() {

    private val viewModel by lazy { getModel<ViewModel>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener { view ->
            doAsync {
                val articleDao = RoomClient.get<ArticleDao>()

                val list = articleDao.getAll()
                list
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
            doAsync {
                RoomClient.get<ArticleDao>().insertAll(
                    Article(
                        content = content.value
                    )
                )
            }
        }
    }
}
