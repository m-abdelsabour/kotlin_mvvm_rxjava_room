package com.kotlin.firstkotlin.db
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepoDao {
    @Query("SELECT * FROM repo")
    fun fetchesAllRepo(): MutableList<Repo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllRepo(repo: MutableList<Repo>)
}