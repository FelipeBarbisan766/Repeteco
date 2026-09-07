package com.repeteco.app.ui

import androidx.camera.video.Recorder
import androidx.camera.video.VideoCapture
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.repeteco.app.camera.CameraController
import com.repeteco.app.camera.CircularBufferManager
import com.repeteco.app.camera.SegmentRecorder
import com.repeteco.app.processing.VideoFileManager

@Composable
fun CameraPreviewScreen(
    onReplayButtonClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()

    val previewView = remember { PreviewView(context) }
    val cameraController = remember { CameraController(context, lifecycleOwner) }
    val videoFileManager = remember { VideoFileManager(context.filesDir) }
    val circularBufferManager = remember { CircularBufferManager() }
    var segmentRecorder by remember { mutableStateOf<SegmentRecorder?>(null) }

    LaunchedEffect(Unit) {
        cameraController.startCamera(previewView) { videoCapture: VideoCapture<Recorder> ->
            val recorder = SegmentRecorder(
                context = context,
                videoCapture = videoCapture,
                videoFileManager = videoFileManager,
                scope = scope
            ) { segment ->
                circularBufferManager.addSegment(segment)
            }
            segmentRecorder = recorder
            recorder.startContinuousRecording()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            segmentRecorder?.stopContinuousRecording()
            cameraController.release()
            circularBufferManager.clear()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { previewView }
        )

        Button(
            onClick = onReplayButtonClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp)
        ) {
            Text("Save Replay")
        }
    }
}
