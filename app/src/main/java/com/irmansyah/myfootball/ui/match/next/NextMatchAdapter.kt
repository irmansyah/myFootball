package com.irmansyah.myfootball.ui.match.next

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
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class NextMatchAdapter (private val match: ArrayList<Match>): RecyclerView.Adapter<NextMatchAdapter.MatchViewHolder>() {

    private lateinit var mListener: (Match) -> Unit

    fun setOnItemCLickListener(listener: (Match) -> Unit) {
        this.mListener = listener
    }

    override fun getItemCount(): Int = match.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
            MatchViewHolder(UI().createView(AnkoContext.create(parent.context, parent)))

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
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
                textView {
                    id = next_date_match
                    allCaps = true
                    gravity = Gravity.CENTER
                    text = "Date"
                }
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    textView {
                        width = wrap_content
                        id = schedule_team_home
                        allCaps = true
                        text = "Team 1"
                        setTypeface(typeface, Typeface.BOLD)
                        gravity = Gravity.CENTER
                    }.lparams {
                        weight = 1F
                    }
                    textView {
                        allCaps = true
                        text = "vs"
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
                }
                view {
                    backgroundColor = resources.getColor(R.color.light_gray)
                }.lparams(width = matchParent, height = dip(1)) {
                    topMargin = dip(8)
                }
            }
        }
    }

    inner class MatchViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        private val homeTeam: TextView = view.find(schedule_team_home)
        private val awayTeam: TextView = view.find(schedule_team_away)
        private val dateMatch: TextView = view.find(next_date_match)

        fun bindItem(match: Match, listener: (Match) -> Unit) {
            homeTeam.text = match.strHomeTeam
            awayTeam.text = match.strAwayTeam
            dateMatch.text = match.dateEvent
            itemView.onClick { listener(match) }
        }
    }
}