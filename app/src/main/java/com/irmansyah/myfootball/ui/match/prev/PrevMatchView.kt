package com.irmansyah.myfootball.ui.match.prev

import com.irmansyah.myfootball.data.model.Match
import com.irmansyah.myfootball.ui.base.MVPView

interface PrevMatchView : MVPView {

    fun showMatchList(data: List<Match>)
}