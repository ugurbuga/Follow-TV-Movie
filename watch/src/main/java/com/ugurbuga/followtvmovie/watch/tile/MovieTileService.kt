package com.ugurbuga.followtvmovie.watch.tile

import androidx.wear.tiles.ActionBuilders
import androidx.wear.tiles.LayoutElementBuilders
import androidx.wear.tiles.ModifiersBuilders
import androidx.wear.tiles.RequestBuilders
import androidx.wear.tiles.ResourceBuilders
import androidx.wear.tiles.TileBuilders
import androidx.wear.tiles.TileService
import androidx.wear.tiles.TimelineBuilders
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import com.ugurbuga.followtvmovie.watch.popularlist.PopularMoviesActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.guava.future

private const val RESOURCES_VERSION = "1"

@AndroidEntryPoint
class MovieTileService : TileService() {
    private val serviceScope = CoroutineScope(Dispatchers.IO)

    override fun onTileRequest(requestParams: RequestBuilders.TileRequest) = serviceScope.future {
        println("Ugur Tile -> onTileRequest")

        centerTextTile("modifier", "Tile")
    }

    override fun onResourcesRequest(requestParams: RequestBuilders.ResourcesRequest): ListenableFuture<ResourceBuilders.Resources> {
        println("Ugur Tile -> onResourcesRequest")
        return Futures.immediateFuture(
            ResourceBuilders.Resources.Builder().setVersion(RESOURCES_VERSION).build()
        )
    }

    private fun centerTextTile(
        modifier: String = "",
        text: String
    ): TileBuilders.Tile {
        println("Ugur Tile -> centerTextTile")
        return TileBuilders.Tile.Builder().setResourcesVersion(RESOURCES_VERSION).setTimeline(
            TimelineBuilders.Timeline.Builder().addTimelineEntry(
                TimelineBuilders.TimelineEntry.Builder().setLayout(
                    LayoutElementBuilders.Layout.Builder().setRoot(
                        centerTextLayout(modifier, text)
                    ).build()
                ).build()
            ).build()
        ).build()
    }

    private fun centerTextLayout(
        modifier: String = "", text: String
    ): LayoutElementBuilders.Column {
        println("Ugur Tile -> centerTextLayout")
        return LayoutElementBuilders.Column.Builder().setModifiers(
            ModifiersBuilders.Modifiers.Builder().setClickable(
                ModifiersBuilders.Clickable.Builder().setId(modifier).setOnClick(
                    ActionBuilders.LaunchAction.Builder().setAndroidActivity(
                        ActionBuilders.AndroidActivity.Builder()
                            .setClassName(PopularMoviesActivity::class.qualifiedName ?: "")
                            .setPackageName(this.packageName).build()
                    ).build()
                ).build()
            ).build()
        ).addContent(
            LayoutElementBuilders.Text.Builder().setMaxLines(5).setText(text).build()
        ).build()
    }
}