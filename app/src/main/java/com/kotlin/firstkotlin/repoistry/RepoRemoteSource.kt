package com.kotlin.firstkotlin.repoistry

import com.kotlin.firstkotlin.core.mapNetworkErrors
import com.kotlin.firstkotlin.db.Repo
import com.kotlin.firstkotlin.network.GitService
import com.kotlin.firstkotlin.network.githubServiceDep
import io.reactivex.Observable

val repoRemoteSourceDep by lazy {
    RepoRemoteSource()
}

class RepoRemoteSource(private val api: GitService = githubServiceDep) : RepoDataSource {
    override fun fetchRepo(userName: String): Observable<MutableList<Repo>> {
        return api.getRepo(userName).mapNetworkErrors()
    }

    override fun saveRepo(repo: MutableList<Repo>) {
    }
}