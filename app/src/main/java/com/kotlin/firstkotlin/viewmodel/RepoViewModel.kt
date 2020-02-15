package com.kotlin.firstkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.firstkotlin.core.addTo
import com.kotlin.firstkotlin.db.Repo
import com.kotlin.firstkotlin.repoistry.RepoRepository
import com.kotlin.firstkotlin.repoistry.repoRepositoryDep
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepoViewModel(private val repo: RepoRepository = repoRepositoryDep) : BaseViewModel() {
    private val repoLiveData = MutableLiveData<MutableList<Repo>>()
    fun getStarRepos(userName: String) {
        repo.fetchRepo(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                setLoading(true)
            }
            .subscribe({
                setLoading(false)
                repoLiveData.value = it
            }, {
                setLoading(false)
                setError(it)
            }, {}).addTo(disposable)
    }

    fun getLiveData(): LiveData<MutableList<Repo>> = repoLiveData
}