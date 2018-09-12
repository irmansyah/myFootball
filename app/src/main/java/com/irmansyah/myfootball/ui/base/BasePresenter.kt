package com.irmansyah.myfootball.ui.base

import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.utils.ScProvider
import com.irmansyah.myfootball.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : MVPView> internal constructor(var dataManager: DataManager, var scProvider: ScProvider) {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    fun onAttach(view: V?) {
        this.view = view
    }

    fun getView(): V? = view

    fun onDetach() {
        compositeDisposable.dispose()
        view = null
    }
}