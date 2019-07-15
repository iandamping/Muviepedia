package com.ian.junemon.spe_learning_mvvm.util

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */


inline fun <reified T> CompositeDisposable.obsExecutes(obs: Observable<T>, crossinline onFailed: (Throwable?) -> Unit, crossinline onSuccess: (T?) -> Unit) {
    this.add(obs.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        //unlimited retry
        .retryWhen { it.flatMap { Observable.timer(5, TimeUnit.SECONDS) } }
        .subscribe({ onSuccess(it) }, { onFailed(it) })
    )
}