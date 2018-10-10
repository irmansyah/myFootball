package com.irmansyah.myfootball.ui.team

import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.ui.base.BasePresenter
import android.util.Log
import com.irmansyah.myfootball.utils.EspressoIdlingResource
import com.irmansyah.myfootball.utils.ScProvider
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class TeamPresenter<V : TeamView> constructor(dataManager: DataManager, scProvider: ScProvider, private val idlingResource: EspressoIdlingResource) : BasePresenter<V>(dataManager = dataManager, scProvider = scProvider) {

    fun getTeamList(leagueName: String?) {
        idlingResource.increment()
        getView()?.showProgress()
        compositeDisposable.add(dataManager.performTeamList(leagueName)
                .compose(scProvider.ioToMainSingleScheduler())
                .subscribe({
                    idlingResource.decrement()
                    getView()?.hideProgress()
                    getView()?.showTeamList(it.teams)
                }, {
                    idlingResource.decrement()
                    Log.e(TeamFragment.TAG, "Team Fragment : ${it.message}")
                    getView()?.hideProgress()

                }))

    }

    fun getSearchTeamList(country: String?, query: String?) {
        getView()?.hideProgress()
        compositeDisposable.add(dataManager.performSearchAllTeamList(country)
                .toObservable()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .flatMap { Observable.fromIterable(it.teams) }
                .filter { it.strTeam?.contains(query.toString(), true)!! }
                .distinct()
                .toList()
                .compose(scProvider.ioToMainSingleScheduler())
                .subscribe({
                    getView()?.showTeamList(it)
                }, {

                    Log.e(TeamFragment.TAG, "Team Fragment : ${it.message}")
                    getView()?.hideProgress()

                }))
    }
}