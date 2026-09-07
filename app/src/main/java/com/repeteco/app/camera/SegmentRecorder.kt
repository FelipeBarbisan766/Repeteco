package com.repeteco.app.camera

import android.annotation.SuppressLint
import android.content.Context
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.core.content.ContextCompat
import com.repeteco.app.processing.VideoFileManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

// PHASE 2: chained short-segment recording (5-10s each)
class SegmentRecorder(
    private val context: Context,
    private val videoCapture: VideoCapture<Recorder>,
    private val videoFileManager: VideoFileManager,
    private val scope: CoroutineScope,
    private val segmentDurationSeconds: Int = 10,
    private val onSegmentFinished: (File) -> Unit
) {

    private var currentRecording: Recording? = null
    private var rotationJob: Job? = null
    private var isRecording = false

    fun startContinuousRecording() {
        isRecording = true
        startNextSegment()
    }

    fun stopContinuousRecording() {
        isRecording = false
        rotationJob?.cancel()
        currentRecording?.stop()
        currentRecording = null
    }

    fun recordExtraSeconds(seconds: Int, onFinished: (File) -> Unit) {
        isRecording = false
        rotationJob?.cancel()
        currentRecording?.stop()
        currentRecording = null

        startSegment(videoFileManager.newSegmentFile(), onFinished)

        rotationJob = scope.launch {
            delay(seconds * 1000L)
            currentRecording?.stop()
            currentRecording = null
        }
    }

    private fun startNextSegment() {
        if (!isRecording) return

        startSegment(videoFileManager.newSegmentFile()) { file ->
            onSegmentFinished(file)
            startNextSegment()
        }

        rotationJob = scope.launch {
            delay(segmentDurationSeconds * 1000L)
            currentRecording?.stop()
        }
    }

    @SuppressLint("MissingPermission")
    private fun startSegment(outputFile: File, onFinalized: (File) -> Unit) {
        val outputOptions = FileOutputOptions.Builder(outputFile).build()

        currentRecording = videoCapture.output
            .prepareRecording(context, outputOptions)
            .withAudioEnabled()
            .start(ContextCompat.getMainExecutor(context)) { event ->
                if (event is VideoRecordEvent.Finalize) {
                    onFinalized(outputFile)
                }
            }
    }
}
