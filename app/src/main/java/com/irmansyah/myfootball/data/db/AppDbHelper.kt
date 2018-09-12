package com.irmansyah.myfootball.data.db

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import android.view.View
import com.irmansyah.myfootball.data.model.FavoriteMatch
import com.irmansyah.myfootball.data.model.FavoriteTeam
import com.irmansyah.myfootball.data.model.Match
import com.irmansyah.myfootball.data.model.Team
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class AppDbHelper(private val context: Context) : DbHelper {

    private var mMatch: Match? = null
    private var team: Team? = null
    private var isFavoritedMatch: Boolean = false
    private var isFavoritedTeam: Boolean = false

    override fun favoriteMatchState(match: Match){
        this.mMatch = match
        context.database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH).whereArgs("(ID_EVENT = {id})", "id" to mMatch?.idEvent!!)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavoritedMatch = true
        }
    }

    override fun favoriteTeamState(team: Team){
        context.database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM).whereArgs("(ID_TEAM = {id})", "id" to team.idTeam!!)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavoritedTeam = true
        }
    }

    override fun addMatchToFavorite() {
        val homeLineup = (mMatch?.strHomeLineupGoalkeeper + "\n" +
                mMatch?.strHomeLineupDefense + "\n" +
                mMatch?.strHomeLineupMidfield + "\n" +
                mMatch?.strHomeLineupForward).replace(";", ",\n")

        val awayLineup = (mMatch?.strAwayLineupGoalkeeper + "\n" +
                mMatch?.strAwayLineupDefense + "\n" +
                mMatch?.strAwayLineupMidfield + "\n" +
                mMatch?.strAwayLineupForward).replace(";", ",\n")

        try {
            context.database.use {
                insert(FavoriteMatch.TABLE_FAVORITE_MATCH,
                        FavoriteMatch.ID_EVENT to mMatch?.idEvent,
                        FavoriteMatch.DATE_EVENT to mMatch?.dateEvent,
                        FavoriteMatch.INT_HOME_GOAL to mMatch?.intHomeScore,
                        FavoriteMatch.INT_AWAY_GOAL to mMatch?.intAwayScore,
                        FavoriteMatch.HOME_TEAM_NAME to mMatch?.strHomeTeam,
                        FavoriteMatch.AWAY_TEAM_NAME to mMatch?.strAwayTeam,
                        FavoriteMatch.HOME_BADGE_TEAM to mMatch?.homeBadge,
                        FavoriteMatch.AWAY_BADGE_TEAM to mMatch?.awayBadge,
                        FavoriteMatch.HOME_GOAL_DETAIL to mMatch?.strHomeGoalDetails,
                        FavoriteMatch.AWAY_GOAL_DETAIL to mMatch?.strAwayGoalDetails,
                        FavoriteMatch.HOME_LINEUP to homeLineup,
                        FavoriteMatch.AWAY_LINEUP to awayLineup)
            }
            Log.i("AppDbHelper", "Added database complete")
            snackbar(context as View, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            Log.i("AppDbHelper", "Added database failed")
            snackbar(context as View, e.localizedMessage).show()
        }
    }

    override fun addTeamToFavorite() {

    }

    override fun removeMatchFromFavorite() {
        try {
            context.database.use {
                delete(FavoriteMatch.TABLE_FAVORITE_MATCH, "(ID_EVENT = {id})",
                        "id" to mMatch?.idEvent!!)
            }
            Log.i("AppDbHelper", "remove database complete")
            snackbar(context as View, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            Log.e("AppDbHelper", "remove database failed")
            snackbar(context as View, e.localizedMessage).show()
        }
    }

    override fun removeTeamFromFavorite() {

    }

}