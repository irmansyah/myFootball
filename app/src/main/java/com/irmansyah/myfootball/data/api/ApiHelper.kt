package com.irmansyah.myfootball.data.api

import com.irmansyah.myfootball.data.model.*
import io.reactivex.Single

interface ApiHelper {

    fun performPrevMatchList(leagueId: String?): Single<MatchResponse>

    fun performNextMatchList(leagueId: String?): Single<MatchResponse>

    fun performSearchMatchList(query: String?): Single<SearchMatchResponse>

    fun performTeamDetail(teamId: String?): Single<DetailTeamResponse>

    fun performTeamList(leagueId: String?): Single<TeamResponse>

    fun performTeamPlayerList(teamId: String?): Single<PlayerResponse>

    fun performSearchAllTeamList(country: String?): Single<TeamResponse>
}