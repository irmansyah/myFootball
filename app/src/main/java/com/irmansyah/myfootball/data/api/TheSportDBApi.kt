package com.irmansyah.myfootball.data.api

import com.irmansyah.myfootball.BuildConfig


object TheSportDBApi {
    
    const val SCHEDULE_PREV = BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php"

    const val SCHEDULE_NEXT = BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php"

    const val DETAIL_TEAM = BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php"

    const val SEARCH_TEAM = BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php"

    const val PLAYER = BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookup_all_players.php"

    const val SEARCH_MATCH = BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchevents.php"
}