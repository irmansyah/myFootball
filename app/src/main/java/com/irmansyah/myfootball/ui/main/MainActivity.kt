package com.irmansyah.myfootball.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.Gravity
import android.view.MenuItem
import android.widget.LinearLayout
import com.irmansyah.myfootball.utils.extension.replaceFragment
import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.R.id.*
import com.irmansyah.myfootball.ui.base.BaseActivity
import com.irmansyah.myfootball.ui.favorite.FavoriteFragment
import com.irmansyah.myfootball.ui.match.MatchFragment
import com.irmansyah.myfootball.ui.match.NextMatchCallback
import com.irmansyah.myfootball.ui.match.PrevMatchCallback
import com.irmansyah.myfootball.ui.team.TeamFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.design.coordinatorLayout
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), MainView, BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        const val TAG = "MainActivity"
    }

    val presenter: MainPresenter<MainView> by inject()

    private lateinit var bottomNav: BottomNavigationView

    lateinit var prevMatchCallback: PrevMatchCallback
    lateinit var nextMatchCallback: NextMatchCallback

    fun setOnPrevMatchRefereshListener(matchCallback: PrevMatchCallback) {
        this.prevMatchCallback = matchCallback
    }

    fun setOnNextMatchRefereshListener(matchCallback: NextMatchCallback) {
        this.nextMatchCallback = matchCallback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onAttach(this)
        MainActivityUI().setContentView(this)

        bottomNav.setOnNavigationItemSelectedListener(this)
        bottomNav.selectedItemId = previous_bottom
    }

    override fun showProgress() { }

    override fun hideProgress() { }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            previous_bottom -> openMatchFragment()
            next_bottom -> openTeamFragment()
            favorite_bottom -> openFavoriteFragment()
        }
        return true
    }

    private fun openMatchFragment() {
        supportFragmentManager?.replaceFragment(f_root_view, MatchFragment.newInstance(), MatchFragment.TAG)
    }

    private fun openTeamFragment() {
        supportFragmentManager?.replaceFragment(f_root_view, TeamFragment.newInstance(), TeamFragment.TAG)
    }

    private fun openFavoriteFragment() {
        supportFragmentManager?.replaceFragment(R.id.f_root_view, FavoriteFragment.newInstance(), FavoriteFragment.TAG)
    }

    inner class MainActivityUI : AnkoComponent<MainActivity> {

        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            coordinatorLayout {
                fitsSystemWindows = true
                relativeLayout {
                    frameLayout {
                        id = f_root_view
                    }.lparams(width = matchParent, height = matchParent) {
                        above(bottom_layout)
                    }
                    linearLayout {
                        id = bottom_layout
                        orientation = LinearLayout.VERTICAL
                        view {
                            backgroundResource = R.drawable.shadow
                        }.lparams(width = matchParent, height = dip(4))
                        bottomNav = bottomNavigationView {
                            id = navigation_bottom
                            topPadding = dip(8)
                            bottomPadding = dip(8)
                            inflateMenu(R.menu.navigation_items)
                            foregroundGravity = Gravity.BOTTOM
                        }.lparams(width = matchParent)
                    }.lparams(width = matchParent) {
                        alignParentBottom()
                    }
                }.lparams(width = matchParent, height = matchParent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
