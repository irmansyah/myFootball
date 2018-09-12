package com.irmansyah.myfootball.ui.match

interface PrevMatchCallback {
    fun fetchPrevMatch(leagueName: String?)
}

interface NextMatchCallback {
    fun fetchNextMatch(leagueName: String?)
}

interface
PlayerCallback {
    fun fetchPlayer(teamId: String?)
}

interface OverviewCallback {
    fun fetchOverview(overview: String?)
}