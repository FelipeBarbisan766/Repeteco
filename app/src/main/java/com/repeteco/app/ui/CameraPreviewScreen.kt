package com.repeteco.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CameraPreviewScreen(
    onReplayButtonClick: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // TODO: PreviewView via AndroidView()

        Button(onClick = onReplayButtonClick) {
            Text("Save Replay")
        }
    }
}
