package com.irmansyah.myfootball.ui.match


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter

import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.data.model.League
import com.irmansyah.myfootball.ui.base.BaseFragment
import com.irmansyah.myfootball.ui.main.MainActivity
import com.irmansyah.myfootball.ui.search.SearchMatchActivity
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.newTask
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor

class MatchFragment : BaseFragment() {

    companion object {
        const val TAG = "MatchFragment"
        fun newInstance(): MatchFragment = MatchFragment()
    }

    private lateinit var matchPagerAdapter: MatchPagerAdapter

    private var leagueName: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        fragmentManager?.let { matchPagerAdapter = MatchPagerAdapter(it) }
        setUpPagerAdapter()

        val list = League.getLeagueSpinnerList()
        val mapLeagueName = list.associateBy ( {it.name}, {it.id} )

        val spinnerItems = resources.getStringArray(R.array.league_array)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        match_spinner.adapter = spinnerAdapter

        match_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = match_spinner.selectedItem.toString()
                (activity as MainActivity).prevMatchCallback.fetchPrevMatch(mapLeagueName[leagueName])
                (activity as MainActivity).nextMatchCallback.fetchNextMatch(mapLeagueName[leagueName])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_match, container, false)

    private fun setUpPagerAdapter() {
        matchPagerAdapter.count = 2
        match_view_pager.adapter = matchPagerAdapter
        tab_layout.addTab(tab_layout.newTab().setText("Prev Match"))
        tab_layout.addTab(tab_layout.newTab().setText("Next Match"))
        match_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) { }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { match_view_pager.currentItem = it.position }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_team, menu)
        val item = menu?.findItem(R.id.action_search)
        true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.action_search -> {
                ctx.startActivity(intentFor<SearchMatchActivity>().newTask())
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
