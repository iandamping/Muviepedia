package com.ian.junemon.spe_learning_mvvm

import android.app.Application
import android.os.Build
import androidx.work.*
import com.google.gson.Gson
import com.ian.junemon.spe_learning_mvvm.api.ApiInterface
import com.ian.junemon.spe_learning_mvvm.di.*
import com.ian.junemon.spe_learning_mvvm.work.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.concurrent.TimeUnit

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
class Apps : Application() {
    companion object {
        val gson: Gson = Gson()
    }

    private val applicationScope = CoroutineScope(Dispatchers.Default)
    override fun onCreate() {
        super.onCreate()
        delayedInit()
        startKoin {
            androidContext(this@Apps)
            modules(listOf(allSourceModule, databaseModule, networkMod, repositoryModule, allVmModule))
        }
    }

    private fun setupRecurringWork() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(true)
                .apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setRequiresDeviceIdle(true)
                    }
                }.build()

        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                BuildConfig.workerName,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingRequest)
    }
    private fun delayedInit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }
}