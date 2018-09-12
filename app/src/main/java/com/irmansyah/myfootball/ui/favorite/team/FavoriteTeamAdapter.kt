package com.irmansyah.myfootball.ui.favorite.team

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.data.model.FavoriteTeam
import com.irmansyah.myfootball.ui.base.BaseViewHolder
import com.training.scoreboard.utils.extension.loadImage
import kotlinx.android.synthetic.main.item_club.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class FavoriteTeamAdapter(private val favorites: ArrayList<FavoriteTeam>) : RecyclerView.Adapter<FavoriteTeamAdapter.FavoriteViewHolder>()  {

    private lateinit var mListener: (FavoriteTeam) -> Unit

    fun setOnItemCLickListener(listener: (FavoriteTeam) -> Unit) {
        this.mListener = listener
    }

    fun addTeamToList(teams: List<FavoriteTeam>) {
        this.favorites.clear()
        this.favorites.addAll(teams)
        notifyDataSetChanged()
    }

    fun clearList() {
        this.favorites.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = favorites.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamAdapter.FavoriteViewHolder =
            FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_club, parent, false))

    override fun onBindViewHolder(holder: FavoriteTeamAdapter.FavoriteViewHolder, position: Int) {
        holder.clear()
        holder.onBind(position)
    }

    inner class FavoriteViewHolder(view: View) : BaseViewHolder(view) {

        override fun clear() {
            itemView.club_img.setImageDrawable(null)
            itemView.club_name_txt.text = ""
        }

        override fun onBind(position: Int) {
            favorites[position].let {
                it.imageUrl?.let { itemView.club_img.loadImage(it) }
                itemView.club_name_txt.text = it.name
            }
            itemView.onClick { mListener(favorites[position]) }
        }
    }
}