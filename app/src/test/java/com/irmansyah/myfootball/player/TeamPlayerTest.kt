package com.irmansyah.myfootball.player

import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.data.model.Player
import com.irmansyah.myfootball.data.model.PlayerResponse
import com.irmansyah.myfootball.ui.detailTeam.player.TeamPlayerPresenter
import com.irmansyah.myfootball.ui.detailTeam.player.TeamPlayerView
import com.irmansyah.myfootball.utils.EspressoIdlingResource
import com.irmansyah.myfootball.utils.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamPlayerTest {

    @Mock
    private lateinit var dataManager: DataManager

    @Mock
    private lateinit var view: TeamPlayerView

    @Mock
    private lateinit var idling: EspressoIdlingResource

    private lateinit var presenter: TeamPlayerPresenter<TeamPlayerView>

    private lateinit var mTestScheduler: TestScheduler

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mTestScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler)

        presenter = TeamPlayerPresenter(dataManager, testSchedulerProvider, idling)
        presenter.onAttach(view)
    }

    @Test
    fun testGetNextMatch() {
        val matches: MutableList<Player> = mutableListOf()
        val response = PlayerResponse(matches)
        val id = "133604"

        Mockito.doReturn(Single.just(response)).`when`(dataManager).performTeamPlayerList(id)

        presenter.getPlayerList(id)

        mTestScheduler.triggerActions()
        Mockito.verify(presenter.getView())?.showPlayerList(matches)

        presenter.onDetach()
    }
}