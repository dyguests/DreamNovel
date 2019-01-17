package com.fanhl.dreamnovel.bizwriting

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.fanhl.dreamnovel.base.ARouters
import com.fanhl.dreamnovel.base.BaseActivity
import com.fanhl.dreamnovel.bizwriting.database.dao.ArticleDao
import com.fanhl.dreamnovel.database.IAppDatabase
import kotlinx.android.synthetic.main.activity_writing.*
import org.jetbrains.anko.doAsync

@Route(path = ARouters.Writing.WRITING)
class WritingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener { view ->
            doAsync {
                val articleDao = IAppDatabase.INSTANCE.get(ArticleDao::class.java) ?: return@doAsync

                val list = articleDao.getAll()
                list
            }
        }
    }
}
