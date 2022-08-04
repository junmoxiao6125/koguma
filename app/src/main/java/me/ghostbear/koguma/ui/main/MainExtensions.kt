/*
 * Copyright (C) 2022 ghostbear
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package me.ghostbear.koguma.ui.main

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory
import me.ghostbear.koguma.KogumaActivity
import me.ghostbear.koguma.domain.model.Manga

@Composable
fun mainViewModel(
    state: MainStateImpl,
    navBackStackEntry: NavBackStackEntry
): MainViewModel {
    val view = LocalView.current
    val factory = EntryPointAccessors.fromActivity(
        (view.context as Activity),
        KogumaActivity.ViewModelFactoryProvider::class.java
    ).mainViewModelFactory()
    return viewModel(
        viewModelStoreOwner = navBackStackEntry,
        factory = HiltViewModelFactory.createInternal(
            (view.context as Activity),
            navBackStackEntry,
            navBackStackEntry.arguments,
            MainViewModel.provideFactory(factory, state)
        )
    )
}

fun MainState.getManga(): Manga {
    return Manga(
        title,
        author,
        artist,
        description,
        genre?.split(",\\s*".toRegex())?.map { it.trim() },
        status
    )
}

fun MainState.setManga(manga: Manga) {
    title = manga.title
    author = manga.author
    artist = manga.artist
    description = manga.description
    genre = manga.genre?.joinToString()
    status = manga.status
}