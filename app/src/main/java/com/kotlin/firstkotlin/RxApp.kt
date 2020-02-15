package com.kotlin.firstkotlin

import android.app.Application

class RxApp : Application() {

    companion object {
        lateinit var INSTANCE: RxApp
    }

    override fun onCreate() {
        super.onCreate()
        Domain.integrateWith(this)
        INSTANCE = this
    }
}