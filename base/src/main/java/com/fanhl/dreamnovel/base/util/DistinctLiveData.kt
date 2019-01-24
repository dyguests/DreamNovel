package com.fanhl.dreamnovel.base.util

import androidx.lifecycle.MutableLiveData
import java.util.*

/**
 * 值相同时不会设值
 */
class DistinctLiveData<T> : MutableLiveData<T>() {
    override fun postValue(value: T?) {
        if (!Objects.equals(getValue(), value)) {
            super.postValue(value)
        }
    }

    override fun setValue(value: T?) {
        if (!Objects.equals(getValue(), value)) {
            super.setValue(value)
        }
    }
}