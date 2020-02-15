package com.kotlin.firstkotlin

import android.app.Application

object Domain {
    lateinit var application: Application private set
    fun integrateWith(application: Application) {
        this.application = application
    }
}
