package com.irmansyah.myfootball.ui.detailTeam.player

import android.util.Log
import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.ui.base.BasePresenter
import com.irmansyah.myfootball.utils.EspressoIdlingResource
import com.irmansyah.myfootball.utils.ScProvider

class TeamPlayerPresenter<V : TeamPlayerView> constructor(dataManager: DataManager, scProvider: ScProvider, private val idlingResource: EspressoIdlingResource) : BasePresenter<V>(dataManager = dataManager, scProvider = scProvider) {

    fun getPlayerList(teamId: String?) {
        idlingResource.increment()
        compositeDisposable.add(dataManager.performTeamPlayerList(teamId)
                .compose(scProvider.ioToMainSingleScheduler())
                .subscribe({ playerResponse ->

                    playerResponse.players?.let { getView()?.showPlayerList(it) }
                    idlingResource.decrement()

                }, {

                    Log.i(TeamPlayerFragment.TAG, "Error team player :: ${it.message}")

                }))
    }
}