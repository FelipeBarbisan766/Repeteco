package com.repeteco.app.camera

import androidx.camera.video.Recorder
import androidx.camera.video.VideoCapture
import java.io.File

// PHASE 2: chained short-segment recording (5-10s each)
class SegmentRecorder(
    private val videoCapture: VideoCapture<Recorder>,
    private val outputDir: File,
    private val segmentDurationSeconds: Int = 10,
    private val onSegmentFinished: (File) -> Unit
) {

    private var isRecording = false

    fun startContinuousRecording() {
        // TODO: Recorder.prepareRecording() + start(), timer-based rotation
        isRecording = true
    }

    fun stopContinuousRecording() {
        isRecording = false
        // TODO: finish current segment
    }

    fun recordExtraSeconds(seconds: Int, onFinished: (File) -> Unit) {
        // TODO: record post-trigger segment
    }
}
