package com.irmansyah.myfootball.data.model

data class LeagueSpinner(var id: String? = null,
                         var name: String? = null,
                         var country: String? = null)

object League {
//    fun getLeagueSpinnerList() : List<LeagueSpinner> = arrayListOf(
//            LeagueSpinner("4328", "English Premier League"),
//            LeagueSpinner("4329", "English League Championship"),
//            LeagueSpinner("4331", "German Bundesliga"),
//            LeagueSpinner("4332", "Italian Serie A"),
//            LeagueSpinner("4334", "French Ligue 1"),
//            LeagueSpinner("4335", "Spanish La Liga"))

    fun getLeagueSpinnerList() : List<LeagueSpinner> = arrayListOf(
            LeagueSpinner("4328", "English Premier League", "England"),
            LeagueSpinner("4329", "English League Championship", "England"),
            LeagueSpinner("4331", "German Bundesliga", "Germany"),
            LeagueSpinner("4332", "Italian Serie A", "Italy"),
            LeagueSpinner("4334", "French Ligue 1", "French"),
            LeagueSpinner("4335", "Spanish La Liga", "Spain"))
}

