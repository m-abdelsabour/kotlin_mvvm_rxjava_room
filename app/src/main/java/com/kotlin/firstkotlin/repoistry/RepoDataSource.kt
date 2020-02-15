package com.kotlin.firstkotlin.repoistry

import com.kotlin.firstkotlin.db.Repo
import io.reactivex.Observable

interface RepoDataSource {
    fun fetchRepo(userName : String) : Observable<MutableList<Repo>>

    fun saveRepo(repo : MutableList<Repo>)
}