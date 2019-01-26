package com.fanhl.dreamnovel.square

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fanhl.dreamnovel.base.BaseFragment
import com.fanhl.dreamnovel.square.adapter.SquareAdapter
import com.fanhl.dreamnovel.square.provider.Recommend
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_square.*

class SquareFragment : BaseFragment() {
    private val adapter by lazy {
        SquareAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_square, container, false)!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        assignViews()
        refreshData()
    }

    private fun assignViews() {
        swipe_refresh_layout.setOnRefreshListener { refreshData() }
        recycler_view.adapter = adapter
    }

    private fun refreshData() {
        Observable
            .create<List<Recommend>> {
                Thread.sleep(1000)
                it.onNext(List(10) { Recommend() })
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    swipe_refresh_layout.isRefreshing = false
                    adapter.setNewData(it)
                },
                onError = {
                    swipe_refresh_layout.isRefreshing = false
                }
            )
            .autoDispose()

//        NetClient.get<SquareService>()
//            .list()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeByNext {
//                toast("测试 list")
//            }
//            .autoDispose()
    }

    companion object {
        fun newInstance(): SquareFragment {
            return SquareFragment()
        }
    }
}