package com.ian.app.muviepedia

import android.app.Application
import com.ian.app.muviepedia.di.injectData
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
class Apps : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Apps)
            injectData()
        }
    }
}