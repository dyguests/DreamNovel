package com.fanhl.dreamnovel.base.util

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.annotations.BackpressureKind
import io.reactivex.annotations.BackpressureSupport
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions

private val onNextStub: (Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = {}
private val onCompleteStub: () -> Unit = {}

private fun <T : Any> ((T) -> Unit).asConsumer(): Consumer<T> {
    return if (this === onNextStub) Functions.emptyConsumer() else Consumer(this)
}

private fun ((Throwable) -> Unit).asOnErrorConsumer(): Consumer<Throwable> {
    return if (this === onErrorStub) Functions.ON_ERROR_MISSING else Consumer(this)
}

private fun (() -> Unit).asOnCompleteAction(): Action {
    return if (this === onCompleteStub) Functions.EMPTY_ACTION else Action(this)
}


/**
 * Overloaded subscribe function that allows passing named parameters
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Observable<T>.subscribeByNext(
    onNext: (T) -> Unit
): Disposable = subscribe(onNext.asConsumer(), onErrorStub.asOnErrorConsumer(), onCompleteStub.asOnCompleteAction())

/**
 * Overloaded subscribe function that allows passing named parameters
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Flowable<T>.subscribeByNext(
    onNext: (T) -> Unit
): Disposable = subscribe(onNext.asConsumer(), onErrorStub.asOnErrorConsumer(), onCompleteStub.asOnCompleteAction())
