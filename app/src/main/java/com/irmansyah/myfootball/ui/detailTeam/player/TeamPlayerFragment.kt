package com.irmansyah.myfootball.ui.detailTeam.player


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.data.model.Player
import com.irmansyah.myfootball.ui.base.BaseFragment
import com.irmansyah.myfootball.ui.detailTeam.DetailTeamActivity
import com.irmansyah.myfootball.ui.match.PlayerCallback
import com.irmansyah.myfootball.ui.player.PlayerActivity
import kotlinx.android.synthetic.main.fragment_team_player.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.koin.android.ext.android.inject

class TeamPlayerFragment : BaseFragment(), TeamPlayerView, PlayerCallback {

    companion object {

        const val TAG = "TeamPlayerFragment"

        fun newInstance(): TeamPlayerFragment = TeamPlayerFragment()
    }

    val presenter : TeamPlayerPresenter<TeamPlayerView> by inject()
    val mAdapter: PlayerAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)
        (activity as DetailTeamActivity).setOnPlayerRefreshListener(this)

        team_player_list_rv.layoutManager = LinearLayoutManager(activity?.applicationContext)
        team_player_list_rv.adapter = mAdapter

        mAdapter.setOnItemCLickListener {
            ctx.startActivity<PlayerActivity>(PlayerActivity.PLAYER_INTENT to it)
        }
    }

    override fun fetchPlayer(teamId: String?) {
        presenter.getPlayerList(teamId)
    }

    override fun showPlayerList(datas: List<Player>?) {
        datas?.let { mAdapter.addPlayerToList(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_team_player, container, false)

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
