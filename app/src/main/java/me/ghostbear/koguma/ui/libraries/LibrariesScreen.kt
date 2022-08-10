package me.ghostbear.koguma.ui.libraries

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.mikepenz.aboutlibraries.entity.Library
import me.ghostbear.koguma.R
import me.ghostbear.koguma.ui.SmallAppBar
import me.ghostbear.koguma.ui.libraries.components.LibraryContainer
import me.ghostbear.koguma.ui.libraries.components.LicenseDialog
import me.ghostbear.koguma.util.plus

@Composable
fun LibrariesScreen(navController: NavHostController) {
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val dialog = rememberSaveable { mutableStateOf<Library?>(null) }

    Scaffold(
        topBar = {
            SmallAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Outlined.ArrowBack, contentDescription = "back")
                    }
                },
                title = {
                    Text(text = stringResource(R.string.licenses))
                },
                scrollBehavior = topAppBarScrollBehavior
            )
        }
    ) { paddingValues ->
        LibraryContainer(
            modifier = Modifier
                .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
            contentPadding = paddingValues + WindowInsets.navigationBars.asPaddingValues(),
            onOpenDialog = { library -> dialog.value = library }
        )
    }

    val library = dialog.value
    if (library != null) {
        LicenseDialog(
            library = library,
            onDismissRequest = { dialog.value = null }
        )
    }
}