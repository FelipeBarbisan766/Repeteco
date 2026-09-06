package com.repeteco.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import java.io.File

@Composable
fun ReplayGalleryScreen(
    replays: List<File> = emptyList(),
    onReplayClick: (File) -> Unit = {},
    onDeleteClick: (File) -> Unit = {}
) {
    Column {
        // TODO: LazyColumn with thumbnail/name/date
    }
}
