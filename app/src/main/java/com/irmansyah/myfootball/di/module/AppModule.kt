package com.irmansyah.myfootball.di.module

import com.irmansyah.myfootball.data.AppDataManager
import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.data.api.ApiHelper
import com.irmansyah.myfootball.data.api.AppApiHelper
import com.irmansyah.myfootball.data.db.AppDbHelper
import com.irmansyah.myfootball.data.db.DbHelper
import com.irmansyah.myfootball.ui.detailTeam.DetailTeamPresenter
import com.irmansyah.myfootball.ui.detailTeam.DetailTeamView
import com.irmansyah.myfootball.ui.detailTeam.player.PlayerAdapter
import com.irmansyah.myfootball.ui.detailTeam.player.TeamPlayerPresenter
import com.irmansyah.myfootball.ui.detailTeam.player.TeamPlayerView
import com.irmansyah.myfootball.ui.favorite.match.FavoriteMatchAdapter
import com.irmansyah.myfootball.ui.favorite.team.FavoriteTeamAdapter
import com.irmansyah.myfootball.ui.favorite.team.FavoriteTeamPresenter
import com.irmansyah.myfootball.ui.favorite.team.FavoriteTeamView
import com.irmansyah.myfootball.ui.main.MainPresenter
import com.irmansyah.myfootball.ui.main.MainView
import com.irmansyah.myfootball.ui.match.next.NextMatchAdapter
import com.irmansyah.myfootball.ui.match.next.NextMatchPresenter
import com.irmansyah.myfootball.ui.match.next.NextMatchView
import com.irmansyah.myfootball.ui.match.prev.PrevMatchAdapter
import com.irmansyah.myfootball.ui.match.prev.PrevMatchPresenter
import com.irmansyah.myfootball.ui.match.prev.PrevMatchView
import com.irmansyah.myfootball.ui.player.PlayerPresenter
import com.irmansyah.myfootball.ui.player.PlayerView
import com.irmansyah.myfootball.ui.search.SearchMatchPresenter
import com.irmansyah.myfootball.ui.search.SearchMatchView
import com.irmansyah.myfootball.ui.team.TeamAdapter
import com.irmansyah.myfootball.ui.team.TeamPresenter
import com.irmansyah.myfootball.ui.team.TeamView
import com.irmansyah.myfootball.utils.EspressoIdlingResource
import com.irmansyah.myfootball.utils.ScProvider
import com.irmansyah.myfootball.utils.SchedulerProvider
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

object AppModule {

    fun getModule(): Module = applicationContext {

        factory { PrevMatchPresenter<PrevMatchView>(get(), get(), get()) }
        factory { NextMatchPresenter<NextMatchView>(get(), get(), get()) }
        factory { FavoriteTeamPresenter<FavoriteTeamView>(get(), get()) }
        factory { TeamPresenter<TeamView>(get(), get(), get()) }
        factory { TeamPlayerPresenter<TeamPlayerView>(get(), get(), get()) }
        factory { SearchMatchPresenter<SearchMatchView>(get(), get()) }
        factory { PlayerPresenter<PlayerView>(get(), get()) }
        factory { DetailTeamPresenter<DetailTeamView>(get(), get()) }
        factory { MainPresenter<MainView>(get(), get()) }

        bean { PrevMatchAdapter(ArrayList()) }
        bean { NextMatchAdapter(ArrayList()) }
        bean { TeamAdapter(ArrayList()) }
        bean { PlayerAdapter(ArrayList()) }
        bean { FavoriteMatchAdapter(ArrayList()) }
        bean { FavoriteTeamAdapter(ArrayList()) }

        bean { AppDataManager(get(), get()) as DataManager }
        bean { AppApiHelper() as ApiHelper }
        bean { AppDbHelper(get()) as DbHelper }
        bean { SchedulerProvider() as ScProvider }
        bean { EspressoIdlingResource() }
    }
}