package com.kotlin.firstkotlin.repoistry

import com.kotlin.firstkotlin.db.AppDataBase
import com.kotlin.firstkotlin.db.Repo
import com.kotlin.firstkotlin.db.appDatabaseDep
import io.reactivex.Observable

val repoLocalSourceDep by lazy {
    RepoLocalSource()
}

class RepoLocalSource(private val db: AppDataBase = appDatabaseDep) : RepoDataSource {
    override fun fetchRepo(userName: String): Observable<MutableList<Repo>> {
        return Observable.fromCallable {
            db.getRepoDao().fetchesAllRepo()
        }
    }

    override fun saveRepo(repo: MutableList<Repo>) {
        db.getRepoDao().saveAllRepo(repo)
    }
}