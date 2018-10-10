package com.irmansyah.myfootball.ui.favorite.team


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.data.db.database
import com.irmansyah.myfootball.data.model.FavoriteTeam
import com.irmansyah.myfootball.data.model.Team
import com.irmansyah.myfootball.ui.base.BaseFragment
import com.irmansyah.myfootball.ui.detailTeam.DetailTeamActivity
import com.irmansyah.myfootball.utils.extension.invisible
import com.irmansyah.myfootball.utils.extension.visible
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.koin.android.ext.android.inject

class FavoriteTeamFragment : BaseFragment(), FavoriteTeamView {

    companion object {
        const val TAG = "FavoriteTeamFragment"
        fun newInstance(): FavoriteTeamFragment = FavoriteTeamFragment()
    }

    val presenter : FavoriteTeamPresenter<FavoriteTeamView> by inject()
    val mAdapter: FavoriteTeamAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)

        favorite_team_list_rv.layoutManager = LinearLayoutManager(activity?.applicationContext)
        favorite_team_list_rv.adapter = mAdapter

        showFavorite()

        mAdapter.setOnItemCLickListener {
            val team = Team()
            team.idTeam = it.idTeam
            team.strTeamBadge = it.imageUrl
            team.strTeam = it.name
            team.strDescriptionEN = it.overview

            ctx.startActivity<DetailTeamActivity>(DetailTeamActivity.DETAIL_TEAM_INTENT to team)
        }
    }

    private fun showFavorite(){
        context?.database?.use {
            hideProgress()
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorites = result.parseList(classParser<FavoriteTeam>())
            mAdapter.addTeamToList(favorites)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_favorite_team, container, false)

    override fun showFavoriteTeamList(datas: List<FavoriteTeam>) {
        swipe_refresh.isRefreshing = false
        mAdapter.addTeamToList(datas)
    }

    override fun showProgress() {
        team_progress.visible()
    }

    override fun hideProgress() {
        team_progress.invisible()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
