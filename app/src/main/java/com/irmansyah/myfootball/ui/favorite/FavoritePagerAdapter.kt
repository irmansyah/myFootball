package com.irmansyah.myfootball.ui.favorite

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.irmansyah.myfootball.ui.favorite.match.FavoriteMatchFragment
import com.irmansyah.myfootball.ui.favorite.team.FavoriteTeamFragment

class FavoritePagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private var tabCount = 0

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> FavoriteMatchFragment.newInstance()
            1 -> FavoriteTeamFragment.newInstance()
            else -> null
        }
    }

    internal fun setCount(count: Int) {
        this.tabCount = count
    }
}