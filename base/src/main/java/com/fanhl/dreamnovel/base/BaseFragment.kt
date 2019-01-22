package com.fanhl.dreamnovel.base

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment() {
    val app by lazy { activity!!.application as BaseApp }

    private val disposes = CompositeDisposable()
    private val disposesMap = mutableMapOf<String, Disposable>()

    override fun onDestroyView() {
        disposes.clear()
        synchronized(disposesMap) {
            disposesMap.values.forEach {
                it.dispose()
            }
            disposesMap.clear()
        }
        super.onDestroyView()
    }

    fun removeDispose(key: String) {
        synchronized(disposesMap) {
            disposesMap.remove(key)?.dispose()
        }
    }

    fun Disposable.autoDispose(key: String? = null) {
        if (key == null) {
            disposes.add(this)
        } else {
            synchronized(disposesMap) {
                disposesMap.put(key, this)
            }
        }
    }
}