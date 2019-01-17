package com.fanhl.dreamnovel.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    val app by lazy { activity!!.application as BaseApp }
}