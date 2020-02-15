package com.kotlin.firstkotlin.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kotlin.firstkotlin.Domain

val appDatabaseDep: AppDataBase by lazy {
    Room.databaseBuilder(Domain.application, AppDataBase::class.java, "repo.db").build()
}

@Database(entities = arrayOf(Repo::class), version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getRepoDao(): RepoDao
}