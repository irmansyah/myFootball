package com.irmansyah.myfootball.ui.main

import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.ui.base.BasePresenter
import com.irmansyah.myfootball.utils.ScProvider

class MainPresenter<V : MainView> constructor(dataManager: DataManager, scProvider: ScProvider) : BasePresenter<V>(dataManager = dataManager, scProvider = scProvider)