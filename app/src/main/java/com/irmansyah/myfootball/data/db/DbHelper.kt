package com.irmansyah.myfootball.data.db

import com.irmansyah.myfootball.data.model.Match
import com.irmansyah.myfootball.data.model.Team

interface DbHelper {

    fun favoriteMatchState(match: Match)

    fun favoriteTeamState(team: Team)

    fun addMatchToFavorite()

    fun addTeamToFavorite()

    fun removeMatchFromFavorite()

    fun removeTeamFromFavorite()
}