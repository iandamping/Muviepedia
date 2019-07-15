package com.ian.junemon.spe_learning_mvvm.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */
abstract class BaseViewModel : ViewModel() {
    /*If you want to use rxjava2 use this,
    * right now our courotine scope is using scope from viewmodel*/
    protected val compose: CompositeDisposable = CompositeDisposable()
    protected fun dispose() {
        if (!compose.isDisposed) compose.dispose()
    }
}