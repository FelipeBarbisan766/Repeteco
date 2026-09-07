package com.repeteco.app.camera

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.VideoCapture
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner

// PHASE 1: CameraX setup, preview + video capture binding
class CameraController(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner
) {

    private var cameraProvider: ProcessCameraProvider? = null
    private var videoCapture: VideoCapture<Recorder>? = null

    fun startCamera(
        previewView: PreviewView,
        onVideoCaptureReady: (VideoCapture<Recorder>) -> Unit = {}
    ) {
        val providerFuture = ProcessCameraProvider.getInstance(context)
        providerFuture.addListener({
            val provider = providerFuture.get()
            cameraProvider = provider

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            val qualitySelector = QualitySelector.from(Quality.HD)
            val recorder = Recorder.Builder()
                .setQualitySelector(qualitySelector)
                .build()
            val capture = VideoCapture.withOutput(recorder)

            provider.unbindAll()
            provider.bindToLifecycle(
                lifecycleOwner,
                CameraSelector.DEFAULT_BACK_CAMERA,
                preview,
                capture
            )
            videoCapture = capture
            onVideoCaptureReady(capture)
        }, ContextCompat.getMainExecutor(context))
    }

    fun release() {
        cameraProvider?.unbindAll()
        cameraProvider = null
        videoCapture = null
    }

    fun getVideoCapture(): VideoCapture<Recorder>? = videoCapture
}
