package com.irmansyah.myfootball.team

import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.data.model.Team
import com.irmansyah.myfootball.data.model.TeamResponse
import com.irmansyah.myfootball.ui.team.TeamPresenter
import com.irmansyah.myfootball.ui.team.TeamView
import com.irmansyah.myfootball.utils.EspressoIdlingResource
import com.irmansyah.myfootball.utils.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamTest {

    @Mock
    private lateinit var dataManager: DataManager

    @Mock
    private lateinit var view: TeamView

    @Mock
    private lateinit var idling: EspressoIdlingResource

    private lateinit var presenter: TeamPresenter<TeamView>

    private lateinit var mTestScheduler: TestScheduler

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mTestScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler)

        presenter = TeamPresenter(dataManager, testSchedulerProvider, idling)
        presenter.onAttach(view)
    }

    @Test
    fun testGetNextMatch() {
        val matches: MutableList<Team> = mutableListOf()
        val response = TeamResponse(matches)
        val league = "English Premier League"

        Mockito.doReturn(Single.just(response)).`when`(dataManager).performTeamList(league)

        presenter.getTeamList(league)

        mTestScheduler.triggerActions()
        Mockito.verify(presenter.getView())?.showProgress()
        Mockito.verify(presenter.getView())?.showTeamList(matches)
        Mockito.verify(presenter.getView())?.hideProgress()

        presenter.onDetach()
    }
}