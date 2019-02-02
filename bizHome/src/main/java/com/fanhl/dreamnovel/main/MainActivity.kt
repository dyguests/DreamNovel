package com.fanhl.dreamnovel.main

import android.os.Bundle
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.fanhl.dreamnovel.base.ARouters
import com.fanhl.dreamnovel.base.BaseActivity
import com.fanhl.dreamnovel.bizhome.R
import com.fanhl.dreamnovel.bookshelf.BookshelfFragment
import com.fanhl.dreamnovel.square.SquareFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = ARouters.Home.MAIN)
class MainActivity : BaseActivity() {
    private val adapter by lazy {
        object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = when (position) {
                0 -> SquareFragment.newInstance()
                1 -> BookshelfFragment.newInstance()
                else -> throw IndexOutOfBoundsException("没有啦，多余的")
            }

            override fun getCount() = 2
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        assignViews()
        initData()
    }

    private fun assignViews() {
        view_pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            private val navigationItemIds = arrayOf(
                R.id.navigation_square,
                R.id.navigation_bookshelf
            )

            override fun onPageSelected(position: Int) {
                navigation.selectedItemId = navigationItemIds[position]
            }
        })

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_square -> {
                    view_pager.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_bookshelf -> {
                    view_pager.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }

    private fun initData() {
        view_pager.adapter = adapter
        view_pager.currentItem = 1
    }
}
