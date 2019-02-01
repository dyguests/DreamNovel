package com.fanhl.dreamnovel.square

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fanhl.dreamnovel.base.BaseFragment
import com.fanhl.dreamnovel.square.adapter.SquareAdapter
import com.fanhl.dreamnovel.square.component.DaggerActivityComponent
import com.fanhl.dreamnovel.square.component.DaggerContainerComponent
import com.fanhl.dreamnovel.square.component.DaggerSquareServiceComponent
import com.fanhl.dreamnovel.square.provider.Recommend
import com.fanhl.dreamnovel.square.service.GithubService
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
//    @Inject
//    lateinit var userModel: UserModel
//    @Inject
//    lateinit var cartModel: ShoppingCartModel

    @Inject
    lateinit var githubService: GithubService

    private val adapter by lazy { SquareAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val activityComponent = DaggerActivityComponent.builder().build()

        val containerComponent = DaggerContainerComponent.builder()
            .activityComponent(activityComponent)
            .build()

        DaggerSquareServiceComponent.builder()
            .activityComponent(activityComponent)
            .containerComponent(containerComponent)
            .build()
            .inject(this)

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
        githubService
            .getRoot()
            .map {
                it.toList().map {
                    val (key, value) = it
                    Recommend(key, value)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    swipe_refresh_layout.isRefreshing = false
                    adapter.setNewData(it)
                },
                onError = {
                    swipe_refresh_layout.isRefreshing = false
                }
            )
            .autoDispose(DISPOSABLE_SQUARE_LIST)

//        Flowable
//            .create<String>({
//                Thread.sleep(3000)
//                it.onNext(userModel.testMethod())
//                Thread.sleep(3000)
//                it.onNext(cartModel.testMethod())
//            }, BackpressureStrategy.LATEST)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy(
//                onNext = {
//                    toast(it)
//                },
//                onError = {
//                    toast(it.message ?: "error")
//                }
//            )
//            .autoDispose()
    }

    companion object {
        /** 获取列表的disposable */
        const val DISPOSABLE_SQUARE_LIST = "DISPOSABLE_SQUARE_LIST"

        fun newInstance(): SquareFragment {
            return SquareFragment()
        }
    }
}