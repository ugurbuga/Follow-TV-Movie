/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ugurbuga.followtvmovie.watch.tile

import androidx.core.content.ContextCompat
import androidx.wear.tiles.ActionBuilders
import androidx.wear.tiles.ColorBuilders.argb
import androidx.wear.tiles.DeviceParametersBuilders.DeviceParameters
import androidx.wear.tiles.DimensionBuilders.dp
import androidx.wear.tiles.LayoutElementBuilders.Box
import androidx.wear.tiles.LayoutElementBuilders.Column
import androidx.wear.tiles.LayoutElementBuilders.FontStyles
import androidx.wear.tiles.LayoutElementBuilders.Image
import androidx.wear.tiles.LayoutElementBuilders.Layout
import androidx.wear.tiles.LayoutElementBuilders.LayoutElement
import androidx.wear.tiles.LayoutElementBuilders.Row
import androidx.wear.tiles.LayoutElementBuilders.Spacer
import androidx.wear.tiles.LayoutElementBuilders.Text
import androidx.wear.tiles.ModifiersBuilders.Background
import androidx.wear.tiles.ModifiersBuilders.Clickable
import androidx.wear.tiles.ModifiersBuilders.Corner
import androidx.wear.tiles.ModifiersBuilders.Modifiers
import androidx.wear.tiles.ModifiersBuilders.Semantics
import androidx.wear.tiles.RequestBuilders.ResourcesRequest
import androidx.wear.tiles.RequestBuilders.TileRequest
import androidx.wear.tiles.ResourceBuilders.AndroidImageResourceByResId
import androidx.wear.tiles.ResourceBuilders.ImageResource
import androidx.wear.tiles.ResourceBuilders.Resources
import androidx.wear.tiles.TileBuilders.Tile
import androidx.wear.tiles.TimelineBuilders.Timeline
import androidx.wear.tiles.TimelineBuilders.TimelineEntry
import com.ugurbuga.followtvmovie.watch.R
import com.ugurbuga.followtvmovie.watch.dao.FavoritesDao
import com.ugurbuga.followtvmovie.watch.ui.detail.MediaType
import com.ugurbuga.followtvmovie.watch.ui.detail.PosterItemUIModel
import com.ugurbuga.followtvmovie.watch.ui.discover.DiscoverActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val RESOURCES_VERSION = "1"

// Dimensions
private const val CIRCLE_SIZE = 48f
private val SPACING_TITLE_SUBTITLE = dp(4f)
private val SPACING_SUBTITLE_DUMMY = dp(12f)
private val ICON_SIZE = dp(24f)

// Resource identifiers for images
private const val ID_IC_SEARCH = "ic_search"

@AndroidEntryPoint
class MovieTileService : CoroutinesTileService() {

    @Inject
    lateinit var favoritesDao: FavoritesDao

    override suspend fun tileRequest(requestParams: TileRequest): Tile {
        val movies = favoritesDao.getFavorites(MediaType.MOVIE, false)
        println("Ugur -> $movies")
        return Tile.Builder()
            .setResourcesVersion(RESOURCES_VERSION)
            .setTimeline(
                Timeline.Builder()
                    .addTimelineEntry(
                        TimelineEntry.Builder()
                            .setLayout(
                                Layout.Builder()
                                    .setRoot(layout(movies, requestParams.deviceParameters!!))
                                    .build()
                            )
                            .build()
                    )
                    .build()
            ).build()
    }

    override suspend fun resourcesRequest(requestParams: ResourcesRequest): Resources {
        return Resources.Builder()
            .setVersion(RESOURCES_VERSION)
            .addIdToImageMapping(
                ID_IC_SEARCH,
                ImageResource.Builder()
                    .setAndroidResourceByResId(
                        AndroidImageResourceByResId.Builder()
                            .setResourceId(R.drawable.ic_ftm_launcher_foreground)
                            .build()
                    )
                    .build()
            )
            .build()
    }

    private fun layout(
        dummy: MutableList<PosterItemUIModel>?,
        deviceParameters: DeviceParameters
    ): LayoutElement = Column.Builder()
        .addContent(
            Text.Builder()
                .setText(dummy?.get(0)?.name ?: "")
                .setFontStyle(
                    FontStyles
                        .title3(deviceParameters)
                        .setColor(
                            argb(ContextCompat.getColor(baseContext, R.color.primary_color))
                        )
                        .build()
                )
                .build()
        )
        .addContent(Spacer.Builder().setHeight(SPACING_TITLE_SUBTITLE).build())
        .addContent(
            Text.Builder()
                .setText(dummy?.get(0)?.releaseDate ?: "")
                .setFontStyle(
                    FontStyles
                        .caption1(deviceParameters)
                        .setColor(
                            argb(ContextCompat.getColor(baseContext, R.color.secondary_color))
                        )
                        .build()
                )
                .build()
        )
        .addContent(Spacer.Builder().setHeight(SPACING_SUBTITLE_DUMMY).build())
        .addContent(
            Row.Builder()
                .addContent(searchLayout())
                .build()
        )
        .setModifiers(
            Modifiers.Builder()
                .setSemantics(
                    Semantics.Builder()
                        .setContentDescription("1")
                        .build()
                )
                .build()
        )
        .build()

    private fun searchLayout() = Box.Builder()
        .setModifiers(
            Modifiers.Builder()
                .setBackground(
                    Background.Builder()
                        .setColor(
                            argb(ContextCompat.getColor(baseContext, R.color.primary_color))
                        )
                        .setCorner(
                            Corner.Builder().setRadius(dp(CIRCLE_SIZE / 2)).build()
                        )
                        .build()
                )
                .setSemantics(
                    Semantics.Builder()
                        .setContentDescription(getString(R.string.tile_description))
                        .build()
                )
                .setClickable(
                    Clickable.Builder().setId("modifier").setOnClick(
                        ActionBuilders.LaunchAction.Builder().setAndroidActivity(
                            ActionBuilders.AndroidActivity.Builder()
                                .setClassName(DiscoverActivity::class.qualifiedName ?: "")
                                .setPackageName(this.packageName).build()
                        ).build()
                    ).build()
                )
                .build()
        )
        .addContent(
            Image.Builder()
                .setWidth(ICON_SIZE)
                .setHeight(ICON_SIZE)
                .setResourceId(ID_IC_SEARCH)
                .build()
        )
        .build()
}