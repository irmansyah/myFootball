package com.irmansyah.myfootball.ui.favorite


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.support.v4.ctx

class FavoriteFragment : BaseFragment() {

    companion object {
        const val TAG = "FavoriteFragment"
        fun newInstance(): FavoriteFragment = FavoriteFragment()
    }

    private lateinit var favoritePagerAdapter: FavoritePagerAdapter

    private var leagueName: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentManager?.let { favoritePagerAdapter = FavoritePagerAdapter(it) }
        setUpPagerAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_favorite, container, false)

    private fun setUpPagerAdapter() {
        favoritePagerAdapter.count = 2
        favorite_view_pager.adapter = favoritePagerAdapter
        tab_layout.addTab(tab_layout.newTab().setText("Match"))
        tab_layout.addTab(tab_layout.newTab().setText("Team"))
        favorite_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) { }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { favorite_view_pager.currentItem = it.position }
            }
        })
    }
}
