package com.irmansyah.myfootball.ui.match.prev

import android.util.Log
import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.ui.base.BasePresenter
import com.irmansyah.myfootball.utils.EspressoIdlingResource
import com.irmansyah.myfootball.utils.ScProvider
import com.irmansyah.myfootball.utils.SchedulerProvider

class PrevMatchPresenter<V : PrevMatchView> constructor(dataManager: DataManager, scProvider: ScProvider, private val idlingResource: EspressoIdlingResource) : BasePresenter<V>(dataManager = dataManager, scProvider = scProvider) {

    fun getPrevMatchList(leagueId: String?) {
        idlingResource.increment()
        getView()?.showProgress()
        compositeDisposable.add(dataManager.performPrevMatchList(leagueId)
                .compose(scProvider.ioToMainSingleScheduler())
                .subscribe ({ matches ->
                    getView()?.let {
                        it.showMatchList(matches.events)
                        it.hideProgress()
                    }
                    idlingResource.decrement()
                }, {
                    getView()?.hideProgress()
                    Log.e(PrevMatchFragment.TAG, "Error PrevMatch")

                }))
    }
}