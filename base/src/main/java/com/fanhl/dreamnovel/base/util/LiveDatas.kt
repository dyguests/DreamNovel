package com.fanhl.dreamnovel.base.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * desc:
 * date: 2018/2/1
 *
 * @author fanhl
 */

/**
 * 简化 observe代码
 */
fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T?) -> Unit) = observe(owner, Observer {
    observer(it)
})

//fun <T> LiveData<T>.asObservable(owner: LifecycleOwner): Observable<T?> {
//    return Observable.create { emitter ->
//        observe(owner) { data ->
//            try {
////                if (data != null) {
//                emitter.onNext(data)
////                } else {
////                    emitter.onError(NullPointerException("com.fanhl.architecturedemo.rxjava2.RxLiveData.asObservable中发送的数据不能为null"))
////                }
//            } catch (e: Exception) {
//                emitter.onError(e)
//            }
//        }
//    }
//}