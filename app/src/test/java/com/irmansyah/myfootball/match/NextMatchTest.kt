package com.irmansyah.myfootball.match

import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.data.model.Match
import com.irmansyah.myfootball.data.model.MatchResponse
import com.irmansyah.myfootball.ui.match.next.NextMatchPresenter
import com.irmansyah.myfootball.ui.match.next.NextMatchView
import com.irmansyah.myfootball.utils.EspressoIdlingResource
import com.irmansyah.myfootball.utils.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify

class NextMatchTest {

    @Mock
    private lateinit var dataManager: DataManager

    @Mock
    private lateinit var view: NextMatchView

    @Mock
    private lateinit var idling: EspressoIdlingResource

    private lateinit var presenter: NextMatchPresenter<NextMatchView>

    private lateinit var mTestScheduler: TestScheduler

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mTestScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler)

        presenter = NextMatchPresenter(dataManager, testSchedulerProvider, idling)
        presenter.onAttach(view)
    }

    @Test
    fun testGetNextMatch() {
        val matches: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matches)
        val id = "4328"

        doReturn(Single.just(response)).`when`(dataManager).performNextMatchList(id)

        presenter.getNextMatchList(id)

        mTestScheduler.triggerActions()
        Mockito.verify(presenter.getView())?.showProgress()
        verify(presenter.getView())?.showMatchList(matches)
        Mockito.verify(presenter.getView())?.hideProgress()

        presenter.onDetach()
    }
}