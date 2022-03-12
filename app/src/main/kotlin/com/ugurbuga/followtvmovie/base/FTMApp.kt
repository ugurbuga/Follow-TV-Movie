package com.ugurbuga.followtvmovie.base

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.BackoffPolicy
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate
import com.ugurbuga.followtvmovie.worker.MovieWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltAndroidApp
class FTMApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    private val localizationDelegate = LocalizationApplicationDelegate()

    override fun attachBaseContext(base: Context) {
        localizationDelegate.setDefaultLanguage(base, Locale.getDefault())
        super.attachBaseContext(localizationDelegate.attachBaseContext(base))
    }

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        localizationDelegate.onConfigurationChanged(this)
    }

    override fun getApplicationContext(): Context {
        return localizationDelegate.getApplicationContext(super.getApplicationContext())
    }

    override fun getResources(): Resources {
        return localizationDelegate.getResources(baseContext, super.getResources())
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startWorker()
    }

    private fun startWorker() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicMovieWork =
            PeriodicWorkRequest.Builder(MovieWorker::class.java, 15, TimeUnit.MINUTES)
                .addTag(MovieWorker.TAG)
                .setConstraints(constraints)
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .build()

        val mWorkManager = WorkManager.getInstance(this)

        mWorkManager.enqueueUniquePeriodicWork(
            MovieWorker.MOVIE_WORKER,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicMovieWork
        )
    }


    companion object {
        lateinit var instance: FTMApp
    }
}
