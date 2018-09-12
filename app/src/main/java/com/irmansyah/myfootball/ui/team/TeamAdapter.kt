package com.irmansyah.myfootball.ui.team

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.data.model.Team
import com.irmansyah.myfootball.ui.base.BaseViewHolder
import com.training.scoreboard.utils.extension.loadImage
import kotlinx.android.synthetic.main.item_club.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class TeamAdapter(private val teams: ArrayList<Team>) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    private lateinit var mListener: (Team) -> Unit

    fun setOnItemCLickListener(listener: (Team) -> Unit) {
        this.mListener = listener
    }

    fun addTeamToList(teams: List<Team>) {
        this.teams.clear()
        this.teams.addAll(teams)
        notifyDataSetChanged()
    }

    fun clearList() {
        this.teams.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = teams.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAdapter.TeamViewHolder =
            TeamViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_club, parent, false))

    override fun onBindViewHolder(holder: TeamAdapter.TeamViewHolder, position: Int) {
        holder.clear()
        holder.onBind(position)
    }

    inner class TeamViewHolder(view: View) : BaseViewHolder(view) {

        override fun clear() {
            itemView.club_img.setImageDrawable(null)
            itemView.club_name_txt.text = ""
        }

        override fun onBind(position: Int) {
            teams[position].let {
                it.strTeamBadge?.let { itemView.club_img.loadImage(it) }
                itemView.club_name_txt.text = it.strTeam
            }
            itemView.onClick { mListener(teams[position]) }
        }
    }
}