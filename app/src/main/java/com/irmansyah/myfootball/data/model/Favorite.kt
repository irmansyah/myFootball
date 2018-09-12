package com.irmansyah.myfootball.data.model

class FavoriteMatch(val id: Long?,
                    val idEvent: String?,
                    val dateEvent: String?,
                    val homeTeamName: String?,
                    val awayTeamName: String?,
                    val homeBadgeTeam: String?,
                    val awayBadgeTeam: String?,
                    val intHomeGoal: String?,
                    val intAwayGoal: String?,
                    val homeGoalDetail: String?,
                    val awayGoalDetail: String?,
                    val homeLineup: String?,
                    val awayLineup: String?) {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val HOME_BADGE_TEAM: String = "HOME_BADGE_TEAM"
        const val AWAY_BADGE_TEAM: String = "AWAY_BADGE_TEAM"
        const val INT_HOME_GOAL: String = "INT_HOME_GOAL"
        const val INT_AWAY_GOAL: String = "INT_AWAY_GOAL"
        const val HOME_GOAL_DETAIL: String = "HOME_GOAL_DETAIL"
        const val AWAY_GOAL_DETAIL: String = "AWAY_GOAL_DETAIL"
        const val HOME_LINEUP: String = "HOME_LINEUP"
        const val AWAY_LINEUP: String = "AWAY_LINEUP"
    }
}

class FavoriteTeam(val id: Long?,
                   val idTeam: String?,
                   val name: String?,
                   val imageUrl: String?,
                   val overview: String?) {

    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val IMAGE_URL: String = "IMAGE_URL"
        const val OVERVIEW: String = "OVERVIEW"
    }
}