package com.irmansyah.myfootball.ui.player

import android.os.Bundle
import com.irmansyah.myfootball.R
import com.irmansyah.myfootball.data.model.Player
import com.irmansyah.myfootball.ui.base.BaseActivity
import com.irmansyah.myfootball.utils.extension.loadImage
import kotlinx.android.synthetic.main.activity_player.*
import org.koin.android.ext.android.inject

class PlayerActivity : BaseActivity(), PlayerView {

    companion object {
        const val TAG = "PlayerActivity"
        const val PLAYER_INTENT = "PLAYER_INTENT"
    }

    val presenter : PlayerPresenter<PlayerView> by inject()

    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        player = intent.getParcelableExtra(PLAYER_INTENT)

        player.strThumb?.let { player_img.loadImage(it) }
        name_tv.text = player.strPlayer
        overview_tv.text = player.strDescriptionEN
    }

    override fun showProgress() { }

    override fun hideProgress() { }
}
