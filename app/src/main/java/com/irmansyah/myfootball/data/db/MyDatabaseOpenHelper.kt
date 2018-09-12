package com.irmansyah.myfootball.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.irmansyah.myfootball.data.model.FavoriteMatch
import com.irmansyah.myfootball.data.model.FavoriteTeam
import org.jetbrains.anko.db.*

class ScheduleDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx,  "Favorite.db", null, 1) {

    companion object {
        private var instance: ScheduleDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): ScheduleDatabaseOpenHelper {
            if (instance == null) {
                instance = ScheduleDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as ScheduleDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.ID_EVENT to TEXT + UNIQUE,
                FavoriteMatch.DATE_EVENT to TEXT,
                FavoriteMatch.HOME_TEAM_NAME to TEXT,
                FavoriteMatch.AWAY_TEAM_NAME to TEXT,
                FavoriteMatch.HOME_BADGE_TEAM to TEXT,
                FavoriteMatch.AWAY_BADGE_TEAM to TEXT,
                FavoriteMatch.INT_HOME_GOAL to TEXT,
                FavoriteMatch.INT_AWAY_GOAL to TEXT,
                FavoriteMatch.HOME_GOAL_DETAIL to TEXT,
                FavoriteMatch.AWAY_GOAL_DETAIL to TEXT,
                FavoriteMatch.HOME_LINEUP to TEXT,
                FavoriteMatch.AWAY_LINEUP to TEXT)

        db.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.ID_TEAM to TEXT + UNIQUE,
                FavoriteTeam.TEAM_NAME to TEXT,
                FavoriteTeam.IMAGE_URL to TEXT,
                FavoriteTeam.OVERVIEW to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
        db.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }
}

val Context.database: ScheduleDatabaseOpenHelper
    get() = ScheduleDatabaseOpenHelper.getInstance(applicationContext)