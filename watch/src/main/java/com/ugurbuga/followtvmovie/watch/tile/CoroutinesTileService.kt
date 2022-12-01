package com.ugurbuga.followtvmovie.watch.tile

import androidx.wear.tiles.RequestBuilders.ResourcesRequest
import androidx.wear.tiles.RequestBuilders.TileRequest
import androidx.wear.tiles.ResourceBuilders.Resources
import androidx.wear.tiles.TileBuilders.Tile
import androidx.wear.tiles.TileService
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.guava.future

/**
 * Base class for a Kotlin and Coroutines friendly TileService.
 */
abstract class CoroutinesTileService : TileService() {
    // For coroutines, use a custom scope we can cancel when the service is destroyed
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    final override fun onTileRequest(
        requestParams: TileRequest
    ): ListenableFuture<Tile> = serviceScope.future {
        tileRequest(requestParams)
    }

    abstract suspend fun tileRequest(requestParams: TileRequest): Tile

    final override fun onResourcesRequest(requestParams: ResourcesRequest):
        ListenableFuture<Resources> = serviceScope.future {
        resourcesRequest(requestParams)
    }

    abstract suspend fun resourcesRequest(requestParams: ResourcesRequest): Resources

    override fun onDestroy() {
        super.onDestroy()
        // Cleans up the coroutine
        serviceJob.cancel()
    }
}
