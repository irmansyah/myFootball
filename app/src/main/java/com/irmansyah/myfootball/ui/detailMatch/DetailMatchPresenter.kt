package com.irmansyah.myfootball.ui.detailMatch

import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.data.model.Match
import com.irmansyah.myfootball.ui.base.BasePresenter
import com.irmansyah.myfootball.utils.ScProvider
import com.irmansyah.myfootball.utils.SchedulerProvider

class DetailMatchPresenter <V : DetailMatchView> constructor(dataManager: DataManager, scProvider: ScProvider) : BasePresenter<V>(dataManager = dataManager, scProvider = scProvider)  {

    fun favoriteMatchState(match: Match) {
        dataManager.favoriteMatchState(match)
    }

    fun addMatchToDatabaseFavorite() {
        dataManager.addMatchToFavorite()
    }
}