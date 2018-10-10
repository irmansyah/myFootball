package com.irmansyah.myfootball.ui.favorite.team

import com.irmansyah.myfootball.data.model.FavoriteTeam
import com.irmansyah.myfootball.ui.base.MVPView

interface FavoriteTeamView : MVPView {

    fun showFavoriteTeamList(datas: List<FavoriteTeam>)
}