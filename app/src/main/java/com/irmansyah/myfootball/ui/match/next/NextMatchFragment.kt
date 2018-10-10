package com.irmansyah.myfootball.ui.match.next


import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar

import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.R.id.*
import com.irmansyah.myfootball.data.model.Match
import com.irmansyah.myfootball.ui.base.BaseFragment
import com.irmansyah.myfootball.ui.detailMatch.DetailMatchActivity
import com.irmansyah.myfootball.ui.main.MainActivity
import com.irmansyah.myfootball.ui.match.NextMatchCallback
import com.irmansyah.myfootball.utils.extension.invisible
import com.irmansyah.myfootball.utils.extension.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.koin.android.ext.android.inject

class NextMatchFragment : BaseFragment(), NextMatchView, NextMatchCallback, AnkoComponent<Context> {

    companion object {
        const val TAG = "NextMatchFragment"
        fun newInstance(): NextMatchFragment = NextMatchFragment()
    }

    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    val presenter : NextMatchPresenter<NextMatchView> by inject()
    val mAdapter: NextMatchAdapter by inject()

    private var mLeagueName: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listEvent.adapter = mAdapter

        presenter.onAttach(this)
        (activity as MainActivity).setOnNextMatchRefereshListener(this)

        swipeRefresh.onRefresh {
            presenter.getNextMatchList(mLeagueName)
        }

        mAdapter.setOnItemCLickListener {
            ctx.startActivity<DetailMatchActivity>(DetailMatchActivity.DETAIL_MATCH_INTENT to it)
        }
    }

    override fun showMatchList(datas: List<Match>) {
        swipeRefresh.isRefreshing = false
        mAdapter.addToList(datas)
    }

    override fun fetchNextMatch(leagueName: String?) {
        leagueName?.let { mLeagueName = it }
        presenter.getNextMatchList(mLeagueName)
    }

    override fun showProgress() {
        progressBar.visible()
    }

    override fun hideProgress() {
        progressBar.invisible()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            createView(AnkoContext.create(ctx))

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            id = next_match_fragment
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listEvent = recyclerView {
                        id = listEventNext
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }
}
