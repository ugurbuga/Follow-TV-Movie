package com.ugurbuga.followtvmovie.worker

import android.app.PendingIntent
import android.content.Context
import androidx.core.os.bundleOf
import androidx.hilt.work.HiltWorker
import androidx.navigation.NavDeepLinkBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.dao.FavoritesDao
import com.ugurbuga.followtvmovie.common.Argument
import com.ugurbuga.followtvmovie.common.Notifier
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class MovieWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val favoritesDao: FavoritesDao
) : Worker(appContext, workerParams) {

    override fun doWork(): Result {

        val list = favoritesDao.getFutureMovies(MediaType.MOVIE, false)

        list.forEach {
            Notifier.postNotification(
                id = it.id.toInt(),
                title = it.name,
                imageUrl = it.posterPath,
                content = Util.getDateString(it.releaseDateLong),
                context = applicationContext,
                intent = getIntent(it.id, it.posterPath),
            )
        }

        return Result.success()
    }

    private fun getIntent(id: String, imageUrl: String): PendingIntent {
        return NavDeepLinkBuilder(applicationContext)
            .setGraph(R.navigation.main_nav_graph)
            .setDestination(R.id.movieDetailFragment)
            .setArguments(bundleOf(Argument.ID to id, Argument.IMAGE_URL to imageUrl))
            .createPendingIntent()
    }

    companion object {
        val TAG: String = MovieWorker::class.java.simpleName
        const val MOVIE_WORKER = "MOVIE_WORKER"
    }

}