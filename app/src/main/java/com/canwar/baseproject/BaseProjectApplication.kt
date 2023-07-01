package com.canwar.baseproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        /* Enable logging in debug */
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}