package com.irmansyah.myfootball.ui.detailTeam

import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.ui.base.BasePresenter
import com.irmansyah.myfootball.utils.ScProvider

class DetailTeamPresenter<V : DetailTeamView> constructor(dataManager: DataManager, scProvider: ScProvider) : BasePresenter<V>(dataManager = dataManager, scProvider = scProvider)