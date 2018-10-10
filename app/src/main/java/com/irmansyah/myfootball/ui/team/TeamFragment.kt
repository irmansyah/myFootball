package com.irmansyah.myfootball.ui.team


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.AdapterView

import android.widget.ArrayAdapter
import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.data.model.League
import com.irmansyah.myfootball.data.model.Team
import com.irmansyah.myfootball.ui.base.BaseFragment
import com.irmansyah.myfootball.ui.detailTeam.DetailTeamActivity
import com.irmansyah.myfootball.ui.main.MainActivity
import com.irmansyah.myfootball.utils.extension.gone
import com.irmansyah.myfootball.utils.extension.visible
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.koin.android.ext.android.inject
import com.miguelcatalan.materialsearchview.MaterialSearchView
import org.jetbrains.anko.startActivity


class TeamFragment : BaseFragment(), TeamView {

    companion object {
        const val TAG = "TeamFragment"
        fun newInstance(): TeamFragment = TeamFragment()
    }

    val presenter : TeamPresenter<TeamView> by inject()
    val mAdapter: TeamAdapter by inject()

    private var leagueName: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        presenter.onAttach(this)

        club_list_rv.layoutManager = LinearLayoutManager(activity?.applicationContext)
        club_list_rv.adapter = mAdapter

        val spinnerItems = resources.getStringArray(R.array.league_array)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        club_spinner.adapter = spinnerAdapter

        club_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = club_spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        swipe_refresh.onRefresh {
            presenter.getTeamList(leagueName)
            team_search_view.closeSearch()
        }

        setSearch()

        mAdapter.setOnItemCLickListener {
            ctx.startActivity<DetailTeamActivity>(DetailTeamActivity.DETAIL_TEAM_INTENT to it)
        }
    }

    private fun setSearch() {
        val list = League.getLeagueSpinnerList()
        val mapCountry = list.associateBy ( {it.name}, {it.country} )

        team_search_view.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.getSearchTeamList(mapCountry[leagueName], newText)
                return true
            }
        })

        team_search_view.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewClosed() {
                presenter.getTeamList(leagueName)
            }

            override fun onSearchViewShown() {
                mAdapter.clearList()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_team, menu)
        val item = menu?.findItem(R.id.action_search)
        team_search_view.setMenuItem(item)
        true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.action_search -> {
                team_search_view.showSearch()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_team, container, false)

    override fun showTeamList(datas: List<Team>) {
        swipe_refresh.isRefreshing = false
        mAdapter.addTeamToList(datas)
    }

    override fun showProgress() {
        team_progress.visible()
    }

    override fun hideProgress() {
        team_progress.gone()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
