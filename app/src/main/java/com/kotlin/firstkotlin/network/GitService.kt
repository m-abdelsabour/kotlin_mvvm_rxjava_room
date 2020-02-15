package com.kotlin.firstkotlin.network

import com.kotlin.firstkotlin.db.Repo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

val githubServiceDep by lazy {
    retrofitDep.create(GitService::class.java)
}

interface GitService {

    @GET("users/{user}/starred")
    fun getRepo(@Path("user") user: String): Observable<MutableList<Repo>>
}