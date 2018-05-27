package com.fanhl.dreamnovel.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentStatePagerAdapter
import com.fanhl.dreamnovel.R
import com.fanhl.dreamnovel.ui.common.BaseActivity
import com.fanhl.dreamnovel.ui.main.fragment.DiscoveryFragment
import com.fanhl.dreamnovel.ui.main.fragment.PlaceholderFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 主页
 */
class MainActivity : BaseActivity() {
    private val adapter by lazy {
        object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = when (position) {
                0 -> PlaceholderFragment.newInstance(position + 1)
                1 -> DiscoveryFragment.newInstance()
                2 -> PlaceholderFragment.newInstance(position + 1)
                else -> throw IndexOutOfBoundsException()
            }

            override fun getCount() = 3
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_pager.adapter = adapter

        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager))

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

    }

    companion object {
        /**
         * 启动activity
         *
         * @param context 上下文
         */
        fun launch(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
