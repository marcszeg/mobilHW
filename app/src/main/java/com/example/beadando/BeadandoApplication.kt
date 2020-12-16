package com.example.beadando

import android.app.Application
import timber.log.Timber

class BeadandoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}