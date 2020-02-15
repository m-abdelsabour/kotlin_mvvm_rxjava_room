package com.kotlin.firstkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.firstkotlin.R
import com.kotlin.firstkotlin.db.Repo
import kotlinx.android.synthetic.main.stars_item.view.*

class GithubRepoAdapter : RecyclerView.Adapter<GithubRepoAdapter.StarRepoViewHolder>() {
    val data = ArrayList<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarRepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stars_item, parent, false)

        return StarRepoViewHolder(view)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: StarRepoViewHolder, position: Int) {
        holder.itemView.desc?.text = data[position].desc
        holder.itemView.lang?.text = data[position].lang
        holder.itemView.repoName?.text = data[position].name
    }

    public fun addRepo(repoArray : MutableList<Repo>){
        data.addAll(repoArray)
        notifyDataSetChanged()
    }


    class StarRepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}