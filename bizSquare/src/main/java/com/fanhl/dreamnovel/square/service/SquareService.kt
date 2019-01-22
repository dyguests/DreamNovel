package com.fanhl.dreamnovel.square.service

import io.reactivex.Flowable
import retrofit2.http.GET

interface SquareService {
    @GET("/square/articles")
    fun list(): Flowable<Any>
}