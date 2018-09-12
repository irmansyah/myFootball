package com.irmansyah.myfootball.ui.favorite.team

import android.util.Log
import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.ui.base.BasePresenter
import com.irmansyah.myfootball.utils.ScProvider
import com.irmansyah.myfootball.utils.SchedulerProvider

class FavoriteTeamPresenter<V : FavoriteTeamView> constructor(dataManager: DataManager, scProvider: ScProvider) : BasePresenter<V>(dataManager = dataManager, scProvider = scProvider) {

    fun getFavoriteTeamList(leagueId: String?) {
//        getView()?.showProgress()
//        compositeDisposable.add(dataManager.performTeamList(leagueId)
//                .compose(schedulerProvider.ioToMainSingleScheduler())
//                .subscribe({
//                    for (team in it.teams) {
//                        Log.i(FavoriteTeamFragment.TAG, "Team Fragment : ${team.strTeamBadge}")
//                    }
//                    getView()?.hideProgress()
//                    getView()?.showFavoriteTeamList(it.teams)
//                }, {
//
//                    Log.e(FavoriteTeamFragment.TAG, "Team Fragment : ${it.message}")
//                    getView()?.hideProgress()
//
//                }))
    }
}