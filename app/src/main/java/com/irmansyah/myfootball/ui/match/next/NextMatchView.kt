package com.irmansyah.myfootball.ui.match.next

import com.irmansyah.myfootball.data.model.Match
import com.irmansyah.myfootball.ui.base.MVPView

interface NextMatchView : MVPView {

    fun showMatchList(data: List<Match>)
}