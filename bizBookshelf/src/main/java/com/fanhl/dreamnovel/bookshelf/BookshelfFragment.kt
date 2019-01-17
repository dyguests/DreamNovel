package com.fanhl.dreamnovel.bookshelf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MediatorLiveData
import com.fanhl.dreamnovel.base.ARouters
import com.fanhl.dreamnovel.base.BaseFragment
import com.fanhl.dreamnovel.base.navigation
import com.fanhl.dreamnovel.base.util.getModel
import com.fanhl.dreamnovel.base.util.observe
import com.fanhl.dreamnovel.bookshelf.adapter.BookshelfAdapter
import com.fanhl.dreamnovel.database.RoomClient
import com.fanhl.dreamnovel.database.dao.writing.ArticleDao
import com.fanhl.dreamnovel.database.entity.writing.Article
import kotlinx.android.synthetic.main.fragment_bookshelf.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * 书架
 *
 * @author fanhl
 */
class BookshelfFragment : BaseFragment() {
    private val headerView by lazy {
        LayoutInflater.from(context).inflate(R.layout.item_bookshelf_add, null as ViewGroup?).apply {
            setOnClickListener {
                ARouters.Writing.WRITING.navigation()
            }
        }
    }

    private val adapter by lazy {
        BookshelfAdapter().apply {
            addHeaderView(headerView)
        }
    }

    private val viewModel by lazy { getModel<ViewModel>() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_bookshelf, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        assignViews()
        initData()
        refreshData()
    }

    private fun assignViews() {
        viewModel.apply {
            articles.observe(this@BookshelfFragment) {
                adapter.setNewData(it)
            }
        }
    }

    private fun initData() {
        recycler_view.adapter = adapter
    }

    private fun refreshData() {
        viewModel.refreshData()
    }

    companion object {
        fun newInstance() = BookshelfFragment()
    }

    class ViewModel : androidx.lifecycle.ViewModel() {
        val articles = MediatorLiveData<List<Article>>()

        fun refreshData() {
            doAsync {
                val articles = RoomClient.get<ArticleDao>().getAll()
                uiThread {
                    this@ViewModel.articles.value = articles
                }
            }
        }
    }
}

