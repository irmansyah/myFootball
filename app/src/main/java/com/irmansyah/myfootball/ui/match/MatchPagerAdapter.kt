package com.irmansyah.myfootball.ui.match

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.irmansyah.myfootball.ui.match.next.NextMatchFragment
import com.irmansyah.myfootball.ui.match.prev.PrevMatchFragment

class MatchPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private var tabCount = 0

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> PrevMatchFragment.newInstance()
            1 -> NextMatchFragment.newInstance()
            else -> null
        }
    }

    internal fun setCount(count: Int) {
        this.tabCount = count
    }

}