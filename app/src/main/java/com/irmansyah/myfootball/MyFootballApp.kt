package com.irmansyah.myfootball

import android.app.Activity
import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.irmansyah.myfootball.di.module.AppModule
import org.koin.android.ext.android.startKoin

class MyFootballApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(AppModule.getModule()))

        AndroidNetworking.initialize(applicationContext)
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)
        }
    }
}