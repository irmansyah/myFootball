package com.irmansyah.myfootball.utils.rx

import com.irmansyah.myfootball.utils.ScProvider
import io.reactivex.*
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider(private val mTestScheduler: TestScheduler) : ScProvider {

    override fun <T> ioToMainObservableScheduler(): ObservableTransformer<T, T>  = ObservableTransformer { upstream ->
        upstream.subscribeOn(io())
                .observeOn(ui())
    }

    override fun <T> ioToMainSingleScheduler(): SingleTransformer<T, T> = SingleTransformer { upstream ->
        upstream.subscribeOn(io())
                .observeOn(ui())
    }

    override fun ioToMainCompletableScheduler(): CompletableTransformer = CompletableTransformer { upstream ->
        upstream.subscribeOn(io())
                .observeOn(ui())
    }

    override fun <T> ioToMainFlowableScheduler(): FlowableTransformer<T, T> = FlowableTransformer { upstream ->
        upstream.subscribeOn(io())
                .observeOn(ui())
    }

    override fun <T> ioToMainMaybeScheduler(): MaybeTransformer<T, T> = MaybeTransformer { upstream ->
        upstream.subscribeOn(io())
                .observeOn(ui())
    }

    fun io(): Scheduler {
        return mTestScheduler
    }

    fun ui(): Scheduler {
        return mTestScheduler
    }
}