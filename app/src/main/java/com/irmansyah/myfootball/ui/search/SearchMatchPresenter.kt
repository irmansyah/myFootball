package com.irmansyah.myfootball.ui.search

import android.util.Log
import com.irmansyah.myfootball.data.DataManager
import com.irmansyah.myfootball.ui.base.BasePresenter
import com.irmansyah.myfootball.utils.ScProvider
import com.irmansyah.myfootball.utils.SchedulerProvider
import java.util.concurrent.TimeUnit

class SearchMatchPresenter<V : SearchMatchView> constructor(dataManager: DataManager, scProvider: ScProvider) : BasePresenter<V>(dataManager = dataManager, scProvider = scProvider) {

    fun setSearchMatch(query: String) {
        compositeDisposable.add(dataManager.performSearchMatchList(query)
                .toObservable()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .compose(scProvider.ioToMainObservableScheduler())
                .subscribe({

                    for (item in it.events!!) {
                        Log.i(SearchMatchActivity.TAG, "Search Match :: ${item.strHomeTeam}")
                    }
                    it.events?.let{ getView()?.showSearchMatchList(it) }

                }, {

                    Log.e(SearchMatchActivity.TAG, "Error Search Match :: ${it.message}")

                }))
    }
}