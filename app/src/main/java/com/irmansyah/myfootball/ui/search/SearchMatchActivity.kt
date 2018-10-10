package com.irmansyah.myfootball.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.data.model.Match
import com.irmansyah.myfootball.ui.base.BaseActivity
import com.irmansyah.myfootball.ui.match.prev.PrevMatchAdapter
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_search_match.*
import org.koin.android.ext.android.inject

class SearchMatchActivity : BaseActivity(), SearchMatchView {

    companion object {
        const val TAG = "SearchMatchActivity"
    }

    val presenter : SearchMatchPresenter<SearchMatchView> by inject()
    val mAdapter : PrevMatchAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)
        setSupportActionBar(toolbar)
        presenter.onAttach(this)

        search_match_list_rv.layoutManager = LinearLayoutManager(applicationContext)
        search_match_list_rv.adapter = mAdapter
    }

    @SuppressLint("CheckResult")
    private fun setSearchView() {
        search_match_search_view.showSearch()
        search_match_search_view.createObservable()
                .compose(presenter.scProvider.ioToMainObservableScheduler())
                .subscribe {

                    presenter.setSearchMatch(it)

                }

        search_match_search_view.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewClosed() {
                finish()
            }

            override fun onSearchViewShown() {}
        })
    }

    override fun showSearchMatchList(datas: List<Match>) {
//        swipeRefresh.isRefreshing = false
        mAdapter.addToList(datas)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.menu_team, menu)
        val item = menu?.findItem(R.id.action_search)
        search_match_search_view.setMenuItem(item)
        setSearchView()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.action_search -> {
                search_match_search_view.showSearch()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showProgress() { }

    override fun hideProgress() { }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
