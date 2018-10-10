package com.irmansyah.myfootball.ui.detailTeam.player

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.data.model.Player
import com.irmansyah.myfootball.ui.base.BaseViewHolder
import com.irmansyah.myfootball.utils.extension.loadImage
import kotlinx.android.synthetic.main.item_player.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class PlayerAdapter (private val players: ArrayList<Player>) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    private lateinit var mListener: (Player) -> Unit

    fun setOnItemCLickListener(listener: (Player) -> Unit) {
        this.mListener = listener
    }

    fun addPlayerToList(players: List<Player>) {
        this.players.clear()
        this.players.addAll(players)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = players.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerAdapter.PlayerViewHolder =
            PlayerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false))

    override fun onBindViewHolder(holder: PlayerAdapter.PlayerViewHolder, position: Int) {
        holder.clear()
        holder.onBind(position)
    }

    inner class PlayerViewHolder(view: View) : BaseViewHolder(view) {

        override fun clear() {
            itemView.player_img.setImageDrawable(null)
            itemView.player_name.text = ""
            itemView.player_pos.text = ""
        }

        override fun onBind(position: Int) {
            players[position].let {
                it.strCutout?.let { itemView.player_img.loadImage(it) }
                itemView.player_name.text = it.strPlayer
                itemView.player_pos.text = it.strPosition
            }
            itemView.onClick { mListener(players[position]) }
        }
    }
}