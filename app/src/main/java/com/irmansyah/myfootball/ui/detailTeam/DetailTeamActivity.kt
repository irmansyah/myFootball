package com.irmansyah.myfootball.ui.detailTeam

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.data.db.database
import com.irmansyah.myfootball.data.model.FavoriteTeam
import com.irmansyah.myfootball.data.model.Team
import com.irmansyah.myfootball.ui.base.BaseActivity
import com.irmansyah.myfootball.ui.match.OverviewCallback
import com.irmansyah.myfootball.ui.match.PlayerCallback
import com.training.scoreboard.utils.extension.loadImage
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.koin.android.ext.android.inject

class DetailTeamActivity : BaseActivity(), DetailTeamView {

    companion object {
        const val TAG = "DetailTeamActivity"
        const val DETAIL_TEAM_INTENT = "DETAIL_TEAM_INTENT"
        const val DETAIL_MATCH_INTENT = "DETAIL_MATCH_INTENT"
    }

    private lateinit var detailTeamPagerAdapter: DetailTeamPagerAdapter

    private var playerCallback: PlayerCallback? = null
    private var overviewCallback: OverviewCallback? = null

    private var isFavoritedTeam: Boolean = false
    private var mTeam: Team? = null

    val presenter : DetailTeamPresenter<DetailTeamView> by inject()

    fun setOnPlayerRefreshListener(playerCallback: PlayerCallback) {
        this.playerCallback = playerCallback
        playerCallback?.fetchPlayer(mTeam?.idTeam)
    }

    fun setOnOverviewRefreshListener(overviewCallback: OverviewCallback) {
        this.overviewCallback = overviewCallback
        overviewCallback?.fetchOverview(mTeam?.strDescriptionEN)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        presenter.onAttach(this)

        supportFragmentManager?.let { detailTeamPagerAdapter = DetailTeamPagerAdapter(it) }
        setUpPagerAdapter()

        mTeam = intent.getParcelableExtra(DETAIL_TEAM_INTENT)
        mTeam?.strTeamBadge?.let { logo_team_img.loadImage(it) }

        setFavorite()
    }

    private fun setUpPagerAdapter() {
        detailTeamPagerAdapter.count = 2
        detail_team_view_pager.adapter = detailTeamPagerAdapter
        tab_layout.addTab(tab_layout.newTab().setText("Overview"))
        tab_layout.addTab(tab_layout.newTab().setText("Player"))
        detail_team_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) { }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { detail_team_view_pager.currentItem = it.position }
            }
        })
    }

    private fun addMatchToFavorite() {
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.ID_TEAM to mTeam?.idTeam,
                        FavoriteTeam.TEAM_NAME to mTeam?.strTeam,
                        FavoriteTeam.IMAGE_URL to mTeam?.strTeamBadge,
                        FavoriteTeam.OVERVIEW to mTeam?.strDescriptionEN)
            }
            Log.i("AppDbHelper", "Added database complete")
            snackbar(logo_team_img, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            Log.i("AppDbHelper", "Added database failed")
            snackbar(logo_team_img, e.localizedMessage).show()
        }
    }

    private fun removeMatchFromFavorite() {
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(ID_TEAM = {id})", "id" to mTeam?.idTeam!!)
            }
            Log.i("AppDbHelper", "remove database complete")
            snackbar(logo_team_img, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            Log.e("AppDbHelper", "remove database failed")
            snackbar(logo_team_img, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM).whereArgs("(ID_TEAM = {id})", "id" to mTeam?.idTeam!!)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavoritedTeam = true
        }

        if (isFavoritedTeam) {
            fab_star.setImageResource(R.drawable.ic_added_to_favorites)
        } else {
            fab_star.setImageResource(R.drawable.ic_add_to_favorites)
        }
        fab_star.setOnClickListener {
            if (isFavoritedTeam) {
                fab_star.setImageResource(R.drawable.ic_add_to_favorites)
                removeMatchFromFavorite()
            } else {
                fab_star.setImageResource(R.drawable.ic_added_to_favorites)
                addMatchToFavorite()
            }
            isFavoritedTeam = !isFavoritedTeam
        }
    }

    override fun showProgress() { }

    override fun hideProgress() { }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
