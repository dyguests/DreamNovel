package com.fanhl.dreamnovel.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fanhl.dreamnovel.R
import com.fanhl.dreamnovel.ui.common.BaseFragment

/**
 * 发现
 */
class DiscoveryFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_discovery, container, false)!!

    companion object {
        fun newInstance(): DiscoveryFragment {
            return DiscoveryFragment()
        }
    }
}