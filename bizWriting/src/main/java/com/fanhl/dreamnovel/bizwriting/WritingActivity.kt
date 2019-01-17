package com.fanhl.dreamnovel.bizwriting

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.fanhl.dreamnovel.base.ARouters
import com.fanhl.dreamnovel.base.BaseActivity
import com.fanhl.dreamnovel.bizwriting.database.dao.ArticleDao
import com.fanhl.dreamnovel.database.RoomClient
import kotlinx.android.synthetic.main.activity_writing.*
import org.jetbrains.anko.doAsync

/**
 * 写日记、文章、。。。
 */
@Route(path = ARouters.Writing.WRITING)
class WritingActivity : BaseActivity() {

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
}
