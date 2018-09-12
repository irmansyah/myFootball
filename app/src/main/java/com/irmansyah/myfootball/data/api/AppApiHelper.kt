package com.irmansyah.myfootball.data.api

import com.irmansyah.myfootball.data.model.*
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class AppApiHelper : ApiHelper {

    override fun performPrevMatchList(leagueId: String?): Single<MatchResponse> =
            Rx2AndroidNetworking.get(TheSportDBApi.SCHEDULE_PREV)
                    .addQueryParameter("id", leagueId)
                    .build()
                    .getObjectSingle(MatchResponse::class.java)

    override fun performNextMatchList(leagueId: String?): Single<MatchResponse> =
            Rx2AndroidNetworking.get(TheSportDBApi.SCHEDULE_NEXT)
                    .addQueryParameter("id", leagueId)
                    .build()
                    .getObjectSingle(MatchResponse::class.java)

    override fun performTeamDetail(teamId: String?): Single<DetailTeamResponse> =
            Rx2AndroidNetworking.get(TheSportDBApi.DETAIL_TEAM)
                    .addQueryParameter("id", teamId)
                    .build()
                    .getObjectSingle(DetailTeamResponse::class.java)

    override fun performTeamList(leagueId: String?): Single<TeamResponse> =
            Rx2AndroidNetworking.get(TheSportDBApi.SEARCH_TEAM)
                    .addQueryParameter("l", leagueId)
                    .build()
                    .getObjectSingle(TeamResponse::class.java)

    override fun performTeamPlayerList(teamId: String?): Single<PlayerResponse> =
            Rx2AndroidNetworking.get(TheSportDBApi.PLAYER)
                    .addQueryParameter("id", teamId)
                    .build()
                    .getObjectSingle(PlayerResponse::class.java)

    override fun performSearchMatchList(query: String?): Single<SearchMatchResponse> =
            Rx2AndroidNetworking.get(TheSportDBApi.SEARCH_MATCH)
                    .addQueryParameter("e", query)
                    .build()
                    .getObjectSingle(SearchMatchResponse::class.java)

    override fun performSearchAllTeamList(country: String?): Single<TeamResponse> =
            Rx2AndroidNetworking.get(TheSportDBApi.SEARCH_TEAM)
                    .addQueryParameter("s", "Soccer")
                    .addQueryParameter("c", country)
                    .build()
                    .getObjectSingle(TeamResponse::class.java)
}