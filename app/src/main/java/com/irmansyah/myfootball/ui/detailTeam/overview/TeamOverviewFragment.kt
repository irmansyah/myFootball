package com.irmansyah.myfootball.ui.detailTeam.overview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.ui.base.BaseFragment
import com.irmansyah.myfootball.ui.detailTeam.DetailTeamActivity
import com.irmansyah.myfootball.ui.match.OverviewCallback
import kotlinx.android.synthetic.main.fragment_team_overview.*

class TeamOverviewFragment : BaseFragment(), OverviewCallback {

    companion object {
        const val TAG = "TeamOverviewFragment"
        fun newInstance(): TeamOverviewFragment = TeamOverviewFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as DetailTeamActivity).setOnOverviewRefreshListener(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_team_overview, container, false)

    override fun fetchOverview(overview: String?) {
        overview_team_tv.text = overview
    }
}
