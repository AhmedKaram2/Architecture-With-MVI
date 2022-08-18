package com.karam.easymvi.core.apiSettings

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EasyMVI : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {
        lateinit var instance: EasyMVI
            private set
    }
}