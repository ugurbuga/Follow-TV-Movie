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

import androidx.annotation.DrawableRes
import kotlinx.coroutines.delay

data class Dummy(
    val id: Long,
    val initials: String,
    val name: String,
    @DrawableRes val avatarRes: Int?
)

object MovieTileRepo {

    suspend fun getFavoriteContacts(): List<Dummy> {
        delay(200)
        return listOf()
    }
}
