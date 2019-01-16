package com.fanhl.dreamnovel.bookshelf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_bookshelf.*

/**
 * 书架
 *
 * @author fanhl
 */
class BookshelfFragment : Fragment() {

    private val adapter by lazy { BookshelfAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_bookshelf, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = adapter

        adapter.setNewData(List(10) { Book() })
    }

    companion object {
        fun newInstance() = BookshelfFragment()
    }
}

class BookshelfAdapter : BaseQuickAdapter<Book, BaseViewHolder>(R.layout.item_bookshelf_diary) {
    override fun convert(helper: BaseViewHolder?, item: Book?) {

    }
}

class Book
