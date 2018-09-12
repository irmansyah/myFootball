package com.irmansyah.myfootball.ui.match.prev

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.R.id.*
import com.irmansyah.myfootball.data.model.Match
import com.irmansyah.myfootball.ui.match.next.NextMatchAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class PrevMatchAdapter(private val match: ArrayList<Match>) : RecyclerView.Adapter<PrevMatchAdapter.MatchViewHolder>() {

    private lateinit var mListener: (Match) -> Unit

    fun setOnItemCLickListener(listener: (Match) -> Unit) {
        this.mListener = listener
    }

    override fun getItemCount(): Int = match.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrevMatchAdapter.MatchViewHolder =
            MatchViewHolder(UI().createView(AnkoContext.create(parent.context, parent)))

    override fun onBindViewHolder(holder: PrevMatchAdapter.MatchViewHolder, position: Int) {
        holder.bindItem(match[position], mListener)
    }

    fun addToList(matches: List<Match>) {
        match.clear()
        match.addAll(matches)
        notifyDataSetChanged()
    }

    inner class UI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            linearLayout {
                orientation = LinearLayout.VERTICAL
                topPadding = dip(8)
                bottomPadding = dip(8)
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    textView {
                        id = schedule_home_score
                        width = wrap_content
                        allCaps = true
                        text = "Team 1"
                        textSize = sp(24).toFloat()
                        setTypeface(typeface, Typeface.BOLD)
                        gravity = Gravity.CENTER
                    }.lparams {
                        weight = 1F
                    }
                    textView {
                        id = R.id.date_match
                        allCaps = true
                        text = "Date"
                    }
                    textView {
                        id = schedule_away_score
                        width = wrap_content
                        allCaps = true
                        text = "Team 2"
                        textSize = sp(24).toFloat()
                        setTypeface(typeface, Typeface.BOLD)
                        gravity = Gravity.CENTER
                    }.lparams {
                        weight = 1F
                    }
                }.lparams(width = matchParent, height = wrapContent)

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    textView {
                        id = schedule_team_home
                        width = wrap_content
                        allCaps = true
                        text = "Team 1"
                        setTypeface(typeface, Typeface.BOLD)
                        gravity = Gravity.CENTER
                    }.lparams {
                        weight = 1F
                    }
                    textView {
                        allCaps = true
                        text = "VS"
                    }
                    textView {
                        id = schedule_team_away
                        width = wrap_content
                        allCaps = true
                        text = "Team 2"
                        setTypeface(typeface, Typeface.BOLD)
                        gravity = Gravity.CENTER
                    }.lparams {
                        weight = 1F
                    }
                }.lparams(width = matchParent, height = wrapContent)
                view {
                    backgroundColor = resources.getColor(R.color.light_gray)
                }.lparams(width = matchParent, height = dip(1)) {
                    topMargin = dip(8)
                }
            }
        }
    }

    inner class MatchViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        private val homeTeamTv: TextView = view.find(schedule_team_home)
        private val awayTeamTv: TextView = view.find(schedule_team_away)
        private val dateTv: TextView = view.find(R.id.date_match)
        private val homeScoreTv: TextView = view.find(schedule_home_score)
        private val awayScoreTv: TextView = view.find(schedule_away_score)

        fun bindItem(match: Match, listener: (Match) -> Unit) {
            homeTeamTv.text = match.strHomeTeam
            awayTeamTv.text = match.strAwayTeam
            dateTv.text = match.dateEvent
            homeScoreTv.text = match.intHomeScore
            awayScoreTv.text = match.intAwayScore

            itemView.onClick { listener(match) }
        }
    }
}