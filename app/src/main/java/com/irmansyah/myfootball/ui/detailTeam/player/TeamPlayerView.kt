package com.irmansyah.myfootball.ui.detailTeam.player

import com.irmansyah.myfootball.data.model.Player
import com.irmansyah.myfootball.ui.base.MVPView

interface TeamPlayerView : MVPView {

    fun showPlayerList(datas: List<Player>?)
}