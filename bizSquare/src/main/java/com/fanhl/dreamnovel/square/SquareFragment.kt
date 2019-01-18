package com.fanhl.dreamnovel.square

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fanhl.dreamnovel.base.BaseFragment

class SquareFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_square, container, false)!!

    companion object {
        fun newInstance(): SquareFragment {
            return SquareFragment()
        }
    }
}