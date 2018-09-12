package com.irmansyah.myfootball.data

import com.irmansyah.myfootball.data.api.ApiHelper
import com.irmansyah.myfootball.data.db.DbHelper
import com.irmansyah.myfootball.data.model.*
import io.reactivex.Single

class AppDataManager(private val apiHelper: ApiHelper, private val dbHelper: DbHelper) : DataManager {

    override fun performPrevMatchList(leagueId: String?): Single<MatchResponse> = apiHelper.performPrevMatchList(leagueId)

    override fun performNextMatchList(leagueId: String?): Single<MatchResponse> = apiHelper.performNextMatchList(leagueId)

    override fun performSearchMatchList(query: String?): Single<SearchMatchResponse> = apiHelper.performSearchMatchList(query)

    override fun performTeamDetail(teamId: String?): Single<DetailTeamResponse> = apiHelper.performTeamDetail(teamId)

    override fun performTeamList(leagueName: String?): Single<TeamResponse> = apiHelper.performTeamList(leagueName)

    override fun performTeamPlayerList(teamId: String?): Single<PlayerResponse> = apiHelper.performTeamPlayerList(teamId)

    override fun performSearchAllTeamList(country: String?): Single<TeamResponse> = apiHelper.performSearchAllTeamList(country)

    override fun favoriteMatchState(match: Match) = dbHelper.favoriteMatchState(match)

    override fun favoriteTeamState(team: Team) = dbHelper.favoriteTeamState(team)

    override fun addMatchToFavorite() = dbHelper.addMatchToFavorite()

    override fun addTeamToFavorite() = dbHelper.addTeamToFavorite()

    override fun removeMatchFromFavorite() = dbHelper.removeMatchFromFavorite()

    override fun removeTeamFromFavorite() = dbHelper.removeTeamFromFavorite()
}