package com.irmansyah.myfootball.ui.favorite.match

import com.irmansyah.myfootball.data.model.FavoriteMatch
import com.irmansyah.myfootball.ui.base.MVPView

interface FavoriteMatchView : MVPView {

    fun showFavoriteTeamList(datas: List<FavoriteMatch>)
}