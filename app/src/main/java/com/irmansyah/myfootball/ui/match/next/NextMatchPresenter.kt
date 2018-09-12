package com.irmansyah.myfootball.ui.match.next

import android.util.Log
import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.ui.base.BasePresenter
import com.irmansyah.myfootball.utils.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import java.util.concurrent.Callable
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import com.irmansyah.myfootball.data.model.*
import com.irmansyah.myfootball.utils.EspressoIdlingResource
import com.irmansyah.myfootball.utils.ScProvider
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import java.util.logging.Logger

class NextMatchPresenter<V : NextMatchView> constructor(dataManager: DataManager, scProvider: ScProvider, private val idlingResource: EspressoIdlingResource) : BasePresenter<V>(dataManager = dataManager, scProvider = scProvider){

    fun getNextMatchList(leagueId: String?) {
        idlingResource.increment()
        getView()?.showProgress()
        compositeDisposable.add(dataManager.performNextMatchList(leagueId)
                .compose(scProvider.ioToMainSingleScheduler())
                .subscribe ({ matches ->
                    getView()?.let {
                        it.hideProgress()
                        it.showMatchList(matches.events)
                    }
                    idlingResource.decrement()
                }, {

                    getView()?.hideProgress()
                    Log.i(NextMatchFragment.TAG, "Error NextMatch ::: ${it.message}")

                }))
    }
}