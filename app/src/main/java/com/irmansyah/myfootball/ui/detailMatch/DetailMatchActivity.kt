package com.irmansyah.myfootball.ui.detailMatch

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.R.id.*
import com.irmansyah.myfootball.data.db.database
import com.irmansyah.myfootball.data.model.FavoriteMatch
import com.irmansyah.myfootball.data.model.Match
import com.irmansyah.myfootball.ui.base.BaseActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.design.snackbar
import org.koin.android.ext.android.inject

class DetailMatchActivity : BaseActivity(), DetailMatchView {

    companion object {
        const val TAG = "DetailMatchActivity"
        const val DETAIL_MATCH_INTENT = "DETAIL_MATCH_INTENT"
    }

    private var mMatch: Match? = null
    private var isFavoritedMatch: Boolean = false


    val presenter : DetailMatchPresenter<DetailMatchView> by inject()

    private lateinit var homeTeamImg: ImageView

    private lateinit var awayTeamImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMatch = intent.getParcelableExtra(DETAIL_MATCH_INTENT)

        setFavorite()

        ActivityUI().setContentView(this)
    }

    private fun setFavorite() {
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH).whereArgs("(ID_EVENT = {id})", "id" to mMatch?.idEvent!!)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavoritedMatch = true
        }
    }

    override fun showProgress() {}

    override fun hideProgress() {}

    inner class ActivityUI : AnkoComponent<DetailMatchActivity> {
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @SuppressLint("SetTextI18n")
        override fun createView(ui: AnkoContext<DetailMatchActivity>) = with(ui) {
            coordinatorLayout {
                relativeLayout {
                    textView {
                        id = R.id.date_tv
                        text = mMatch?.dateEvent
                        gravity = Gravity.CENTER
                        textSize = 22f
                        setTypeface(typeface, Typeface.ITALIC)
                    }.lparams(width = matchParent)
                    linearLayout {
                        id = R.id.layout_1
                        orientation = LinearLayout.HORIZONTAL
                        homeTeamImg = imageView {
                            id = R.id.home_team_img
                            scaleType = ImageView.ScaleType.CENTER_CROP
                        }.lparams {
                            width = 100
                            height = 100
                            leftMargin = dip(16)
                            gravity = Gravity.CENTER
                        }
                        textView {
                            id = R.id.home_score_tv
                            text = mMatch?.intHomeScore
                            gravity = Gravity.CENTER
                            textSize = 42f
                            setTypeface(typeface, Typeface.BOLD)
                        }.lparams(width = dip(0)) {
                            weight = 1f
                            gravity = Gravity.CENTER
                        }
                        textView {
                            id = R.id.vs_tv
                            text = "vs"
                            gravity = Gravity.CENTER
                            textSize = 28f //sp
                        }.lparams(width = dip(0)) {
                            weight = 1f
                            gravity = Gravity.CENTER
                        }
                        textView {
                            id = R.id.away_score_tv
                            text = mMatch?.intAwayScore
                            gravity = Gravity.CENTER
                            textSize = 42f //sp
                            setTypeface(typeface, Typeface.BOLD)
                        }.lparams(width = dip(0)) {
                            weight = 1f
                            gravity = Gravity.CENTER
                        }
                        awayTeamImg = imageView {
                            id = R.id.away_team_img
                            scaleType = ImageView.ScaleType.CENTER_CROP
                        }.lparams {
                            width = 100
                            height = 100
                            rightMargin = dip(16)
                            gravity = Gravity.CENTER
                        }
                    }.lparams(width = matchParent) {
                        below(R.id.date_tv)
                    }
                    linearLayout {
                        id = R.id.layout_2
                        orientation = LinearLayout.HORIZONTAL
                        textView {
                            id = R.id.home_name_tv
                            text = mMatch?.strHomeTeam
                            gravity = Gravity.CENTER
                            textSize = 22f //sp
                        }.lparams(width = dip(0)) {
                            weight = 1f
                            gravity = Gravity.CENTER
                        }
                        textView {
                            id = R.id.away_name_tv
                            text = mMatch?.strAwayTeam
                            gravity = Gravity.CENTER
                            textSize = 22f //sp
                        }.lparams(width = dip(0)) {
                            weight = 1f
                            gravity = Gravity.CENTER
                        }
                    }.lparams(width = matchParent) {
                        below(R.id.layout_1)
                    }
                    view {
                        id = R.id.view
                        backgroundColor = resources.getColor(android.R.color.darker_gray)
                    }.lparams(width = matchParent, height = dip(1)) {
                        below(R.id.layout_2)
                        leftMargin = dip(16)
                        rightMargin = dip(16)
                        topMargin = dip(16)
                        bottomMargin = dip(16)
                    }
                    textView {
                        id = R.id.goal_scorer_tv
                        gravity = Gravity.CENTER
                        text = "Scorer"
                    }.lparams(width = matchParent) {
                        below(R.id.view)
                    }
                    relativeLayout {
                        id = R.id.goal_scorer_layout
                        textView {
                            id = R.id.detail_goal_home_name_tv
                            gravity = Gravity.LEFT
                            text = (mMatch?.strHomeGoalDetails)?.replace(";", ",\n")
                        }.lparams {
                            leftMargin = dip(16)
                        }
                        textView {
                            id = R.id.detail_goal_away_name_tv
                            gravity = Gravity.RIGHT
                            text = (mMatch?.strAwayGoalDetails)?.replace(";", ",\n")
                        }.lparams {
                            rightMargin = dip(16)
                            alignParentEnd()
                        }
                    }.lparams(width = matchParent) {
                        below(R.id.goal_scorer_tv)
                    }
                    view {
                        id = R.id.view2
                        backgroundColor = resources.getColor(android.R.color.darker_gray)
                    }.lparams(width = matchParent, height = dip(1)) {
                        below(R.id.goal_scorer_layout)
                        leftMargin = dip(16)
                        rightMargin = dip(16)
                        topMargin = dip(16)
                        bottomMargin = dip(16)
                    }
                    textView {
                        id = R.id.lineup_tv
                        gravity = Gravity.CENTER
                        text = "Lineup"
                    }.lparams(width = matchParent) {
                        below(R.id.view2)
                    }
                    relativeLayout {
                        id = R.id.lineup_layout
                        textView {
                            id = R.id.detail_lineup_home_tv
                            gravity = Gravity.LEFT
                            val txt = mMatch?.strHomeLineupGoalkeeper + "\n" +
                                    mMatch?.strHomeLineupDefense + "\n" +
                                    mMatch?.strHomeLineupMidfield + "\n" +
                                    mMatch?.strHomeLineupForward
                            val textAfterChange = txt.replace(";", ",\n")
                            text = textAfterChange
                        }.lparams {
                            leftMargin = dip(16)
                        }
                        textView {
                            id = R.id.detail_lineup_away_tv
                            gravity = Gravity.RIGHT
                            val txt = mMatch?.strAwayLineupGoalkeeper + "\n" +
                                    mMatch?.strAwayLineupDefense + "\n" +
                                    mMatch?.strAwayLineupMidfield + "\n" +
                                    mMatch?.strAwayLineupForward
                            val textAfterChange = txt.replace(";", ",\n")
                            text = textAfterChange
                        }.lparams {
                            rightMargin = dip(16)
                            alignParentEnd()
                        }
                    }.lparams(width = matchParent) {
                        below(R.id.lineup_tv)
                    }
                }
                floatingActionButton {
                    id = add_to_favorite_match
                    setBackgroundColor(resources.getColor(R.color.colorPrimary))
                    backgroundTintList = ContextCompat.getColorStateList(ctx, R.color.colorPrimary)
                    Log.i(TAG, "isFavoritedMatch $isFavoritedMatch")
                    if (isFavoritedMatch) {
                        imageResource = R.drawable.ic_added_to_favorites
                    } else {
                        imageResource = R.drawable.ic_add_to_favorites
                    }
                    setOnClickListener {
                        if (isFavoritedMatch) {
                            imageResource = R.drawable.ic_add_to_favorites
                            removeMatchFromFavorite()
                        } else {
                            imageResource = R.drawable.ic_added_to_favorites
                            addMatchToFavorite()
                        }
                        isFavoritedMatch = !isFavoritedMatch
                    }
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    rightMargin = dip(24)
                    bottomMargin = dip(24)
                    gravity = Gravity.BOTTOM or Gravity.END
                }
            }
        }
    }

    private fun addMatchToFavorite() {
        val homeLineup = (mMatch?.strHomeLineupGoalkeeper + "\n" +
                mMatch?.strHomeLineupDefense + "\n" +
                mMatch?.strHomeLineupMidfield + "\n" +
                mMatch?.strHomeLineupForward).replace(";", ",\n")

        val awayLineup = (mMatch?.strAwayLineupGoalkeeper + "\n" +
                mMatch?.strAwayLineupDefense + "\n" +
                mMatch?.strAwayLineupMidfield + "\n" +
                mMatch?.strAwayLineupForward).replace(";", ",\n")

        try {
            database.use {
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
            snackbar(homeTeamImg, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            Log.i("AppDbHelper", "Added database failed")
            snackbar(homeTeamImg, e.localizedMessage).show()
        }
    }

    private fun removeMatchFromFavorite() {
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE_MATCH, "(ID_EVENT = {id})",
                        "id" to mMatch?.idEvent!!)
            }
            Log.i("AppDbHelper", "remove database complete")
            snackbar(homeTeamImg, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            Log.e("AppDbHelper", "remove database failed")
            snackbar(homeTeamImg, e.localizedMessage).show()
        }
    }
}
