package com.irmansyah.myfootball.ui.detailMatch

import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.ui.base.BasePresenter
import com.irmansyah.myfootball.utils.ScProvider

class DetailMatchPresenter <V : DetailMatchView> constructor(dataManager: DataManager, scProvider: ScProvider) : BasePresenter<V>(dataManager = dataManager, scProvider = scProvider)