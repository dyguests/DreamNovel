package com.fanhl.dreamnovel.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.fanhl.dreamnovel.R
import com.fanhl.dreamnovel.ui.common.BaseActivity
import com.fanhl.dreamnovel.ui.read.ReadActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        assignViews()
        initData()
    }

    private fun assignViews() {
        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
//                    message.setText(R.string.title_home)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
//                    message.setText(R.string.title_dashboard)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
//                    message.setText(R.string.title_notifications)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

//        btn_read.setOnClickListener {
//            ReadActivity.launch(this@MainActivity)
//        }
    }

    private fun initData() {
        view_pager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getCount(): Int {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
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
