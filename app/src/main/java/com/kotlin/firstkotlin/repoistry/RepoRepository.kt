package com.kotlin.firstkotlin.repoistry

import com.kotlin.firstkotlin.db.Repo
import io.reactivex.Observable
import java.util.concurrent.Executors

val repoRepositoryDep by lazy {
    RepoRepository()
}

class RepoRepository(
    private val remoteSource: RepoRemoteSource = repoRemoteSourceDep,
    private val localSource: RepoLocalSource = repoLocalSourceDep) :
    RepoDataSource {
    override fun fetchRepo(userName: String): Observable<MutableList<Repo>> {
        return Observable.concatArray(
            localSource.fetchRepo(userName),
            remoteSource.fetchRepo(userName).doOnNext {
                saveRepo(it)
            }
        )
    }

    override fun saveRepo(repo: MutableList<Repo>) {
        Executors.newSingleThreadExecutor().execute{
            localSource.saveRepo(repo)
        }
    }
}