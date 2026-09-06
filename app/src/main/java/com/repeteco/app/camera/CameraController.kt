package com.repeteco.app.camera

import androidx.camera.video.Recorder
import androidx.camera.video.VideoCapture
import androidx.lifecycle.LifecycleOwner

// PHASE 1: CameraX setup, preview + video capture binding
class CameraController(
    private val lifecycleOwner: LifecycleOwner
) {

    private var videoCapture: VideoCapture<Recorder>? = null

    fun startCamera() {
        // TODO: get ProcessCameraProvider, configure Recorder,
        // bind to lifecycle with CameraSelector.DEFAULT_BACK_CAMERA
    }

    fun release() {
        // TODO: unbind camera provider
    }

    fun getVideoCapture(): VideoCapture<Recorder>? = videoCapture
}
