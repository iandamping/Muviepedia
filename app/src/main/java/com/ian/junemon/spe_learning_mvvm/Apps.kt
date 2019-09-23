package com.ian.junemon.spe_learning_mvvm

import android.app.Application
import com.google.gson.Gson
import com.ian.junemon.spe_learning_mvvm.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
class Apps : Application() {
    companion object {
        val gson: Gson = Gson()
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Apps)
            modules(listOf(allSourceModule, databaseModule, networkMod, repositoryModule, allVmModule))
        }
    }
}