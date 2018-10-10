package com.irmansyah.myfootball.ui.favorite.team

import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.ui.base.BasePresenter
import com.irmansyah.myfootball.utils.ScProvider

class FavoriteTeamPresenter<V : FavoriteTeamView> constructor(dataManager: DataManager, scProvider: ScProvider) : BasePresenter<V>(dataManager = dataManager, scProvider = scProvider)