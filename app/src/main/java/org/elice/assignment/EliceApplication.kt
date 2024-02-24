package org.elice.assignment

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EliceApplication: Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: EliceApplication
        fun getApplicationContext(): Context {
            return instance.applicationContext
        }
    }
}