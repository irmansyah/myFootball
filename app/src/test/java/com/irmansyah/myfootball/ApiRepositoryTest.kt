package com.irmansyah.myfootball

import com.irmansyah.myfootball.ui.match.next.NextMatchPresenter
import com.irmansyah.myfootball.ui.match.prev.PrevMatchPresenter
import com.irmansyah.myfootball.ui.team.TeamPresenter
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ApiRepositoryTest {

    @Test
    fun testDoRequestNextMatchList() {
        val apiRepository = Mockito.mock(NextMatchPresenter::class.java)
        val id = "4328"
        apiRepository.getNextMatchList(id)
        Mockito.verify(apiRepository).getNextMatchList(id)
    }

    @Test
    fun testDoRequestPrevMatchList() {
        val apiRepository = Mockito.mock(PrevMatchPresenter::class.java)
        val id = "4328"
        apiRepository.getPrevMatchList(id)
        Mockito.verify(apiRepository).getPrevMatchList(id)
    }

    @Test
    fun testDoRequestTeamList() {
        val apiRepository = Mockito.mock(TeamPresenter::class.java)
        val id = "English Premier League"
        apiRepository.getTeamList(id)
        Mockito.verify(apiRepository).getTeamList(id)
    }
}