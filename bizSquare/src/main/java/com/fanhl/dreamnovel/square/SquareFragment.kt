package com.fanhl.dreamnovel.square

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fanhl.dreamnovel.base.BaseFragment
import com.fanhl.dreamnovel.base.util.toast
import com.fanhl.dreamnovel.square.adapter.SquareAdapter
import com.fanhl.dreamnovel.square.component.ActivityComponent
import com.fanhl.dreamnovel.square.component.DaggerActivityComponent
import com.fanhl.dreamnovel.square.model.UserModel
import com.fanhl.dreamnovel.square.module.ActivityModule
import com.fanhl.dreamnovel.square.provider.Recommend
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_square.*
import javax.inject.Inject


/**
 * 广场
 *
 * @author fanhl
 */
class SquareFragment : BaseFragment() {
    private var mActivityComponent: ActivityComponent? = null

    @Inject
    lateinit var userModel: UserModel

    private val adapter by lazy {
        SquareAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mActivityComponent = DaggerActivityComponent.builder().activityModule(ActivityModule()).build()
        return inflater.inflate(R.layout.fragment_square, container, false)!!
    }

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
        removeDispose(DISPOSABLE_SQUARE_LIST)
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
            .autoDispose(DISPOSABLE_SQUARE_LIST)

//        NetClient.get<SquareService>()
//            .list()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeByNext {
//                toast("测试 list")
//            }
//            .autoDispose()

        Single
            .create<String> {
                Thread.sleep(2000)
                it.onSuccess(userModel.testMethod())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    toast(it)
                }
            )
            .dispose()
    }

    companion object {
        /** 获取列表的disposable */
        const val DISPOSABLE_SQUARE_LIST = "DISPOSABLE_SQUARE_LIST"

        fun newInstance(): SquareFragment {
            return SquareFragment()
        }
    }
}