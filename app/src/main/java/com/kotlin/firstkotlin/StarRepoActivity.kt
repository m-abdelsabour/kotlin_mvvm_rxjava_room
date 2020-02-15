package com.kotlin.firstkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.kotlin.firstkotlin.adapter.GithubRepoAdapter
import com.kotlin.firstkotlin.core.showMessage
import com.kotlin.firstkotlin.viewmodel.RepoViewModel
import kotlinx.android.synthetic.main.activity_star_repo.*

class StarRepoActivity : AppCompatActivity() {
    lateinit var repoAdapter: GithubRepoAdapter
    lateinit var repoViewModel: RepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_repo)
        repoViewModel = ViewModelProviders.of(this).get(RepoViewModel::class.java)
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerStar.addItemDecoration(divider)
        repoAdapter = GithubRepoAdapter()
        recyclerStar.adapter = repoAdapter

        getStarRepo(repoViewModel)
        observeRepo(repoViewModel)

        initObservations()
    }

    private fun initObservations() {
        repoViewModel.getError().observe(this, errorObserver)
        repoViewModel.getLoading().observe(this, loadingObserver)
    }

    private fun getStarRepo(repoViewModel: RepoViewModel) {
        repoViewModel.getStarRepos("mrabelwahed")
    }

    private fun observeRepo(repoViewModel: RepoViewModel) {
        repoViewModel.getLiveData().observe(this, Observer {
            repoAdapter.addRepo(it)
        })
    }

    private val errorObserver = Observer<Throwable> {
        showMessage(it, false)
    }
    private val loadingObserver = Observer<Boolean> {


    }
}
