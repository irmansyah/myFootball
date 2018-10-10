package com.irmansyah.myfootball.ui.favorite.match


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.data.db.database
import com.irmansyah.myfootball.data.model.FavoriteMatch
import com.irmansyah.myfootball.data.model.Match
import com.irmansyah.myfootball.ui.base.BaseFragment
import com.irmansyah.myfootball.ui.detailMatch.DetailMatchActivity
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.koin.android.ext.android.inject

class FavoriteMatchFragment : BaseFragment(), FavoriteMatchView {

    companion object {
        const val TAG = "FavoriteMatchFragment"
        fun newInstance(): FavoriteMatchFragment = FavoriteMatchFragment()
    }

    val mAdapter: FavoriteMatchAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favorite_match_list_rv.layoutManager = LinearLayoutManager(activity?.applicationContext)
        favorite_match_list_rv.adapter = mAdapter

        showFavorite()

        mAdapter.setOnItemCLickListener {
            val match = Match()
            match.idEvent = it.idEvent
            match.dateEvent = it.dateEvent
            match.strHomeTeam = it.homeTeamName
            match.strAwayTeam = it.awayTeamName
            match.homeBadge = it.homeBadgeTeam
            match.awayBadge = it.awayBadgeTeam
            match.intHomeScore = it.intHomeGoal
            match.intAwayScore = it.intAwayGoal
            match.strHomeGoalDetails = it.homeGoalDetail
            match.strAwayGoalDetails = it.awayGoalDetail
            match.strHomeLineupGoalkeeper = it.homeLineup
            match.strHomeLineupDefense = ""
            match.strHomeLineupMidfield = ""
            match.strHomeLineupForward = ""
            match.strAwayLineupGoalkeeper = it.awayLineup
            match.strAwayLineupDefense = ""
            match.strAwayLineupMidfield = ""
            match.strAwayLineupForward = ""

            ctx.startActivity<DetailMatchActivity>(DetailMatchActivity.DETAIL_MATCH_INTENT to match)
        }
    }

    private fun showFavorite(){
        context?.database?.use {
            hideProgress()
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favorites = result.parseList(classParser<FavoriteMatch>())
            mAdapter.addToList(favorites)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_favorite_match, container, false)

    override fun showFavoriteTeamList(datas: List<FavoriteMatch>) {
        mAdapter.addToList(datas)
    }

    override fun showProgress() { }

    override fun hideProgress() { }
}
