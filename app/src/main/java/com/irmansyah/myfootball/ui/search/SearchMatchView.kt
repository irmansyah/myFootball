package com.irmansyah.myfootball.ui.search

import com.irmansyah.myfootball.data.model.Match
import com.irmansyah.myfootball.ui.base.MVPView

interface SearchMatchView : MVPView {

    fun showSearchMatchList(datas: List<Match>)
}