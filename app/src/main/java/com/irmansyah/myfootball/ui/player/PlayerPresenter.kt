package com.irmansyah.myfootball.ui.player

import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.ui.base.BasePresenter
import com.irmansyah.myfootball.utils.ScProvider

class PlayerPresenter<V : PlayerView> constructor(dataManager: DataManager, scProvider: ScProvider) : BasePresenter<V>(dataManager = dataManager, scProvider = scProvider)