package com.fanhl.dreamnovel.square.service

import io.reactivex.Single
import retrofit2.http.GET

/**
 * FIXME 这个类用仅来测试用
 */
interface GithubService {
    @GET("https://api.github.com/")
    fun getRoot(): Single<Map<String, String>>
}