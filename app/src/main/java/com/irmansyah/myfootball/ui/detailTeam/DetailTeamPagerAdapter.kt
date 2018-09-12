package com.irmansyah.myfootball.ui.detailTeam

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.irmansyah.myfootball.ui.detailTeam.overview.TeamOverviewFragment
import com.irmansyah.myfootball.ui.detailTeam.player.TeamPlayerFragment

class DetailTeamPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private var tabCount = 0

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> TeamOverviewFragment.newInstance()
            1 -> TeamPlayerFragment.newInstance()
            else -> null
        }
    }

    internal fun setCount(count: Int) {
        this.tabCount = count
    }

}