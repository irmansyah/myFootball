package com.irmansyah.myfootball.ui.team

import com.irmansyah.myfootball.data.model.Team
import com.irmansyah.myfootball.ui.base.MVPView

interface TeamView : MVPView {

    fun showTeamList(datas: List<Team>)
}