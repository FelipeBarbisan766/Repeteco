package com.repeteco.app.processing

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class VideoFileManager(
    private val baseDir: File
) {

    private val segmentsDir: File
        get() = File(baseDir, "segments").apply { mkdirs() }

    private val replaysDir: File
        get() = File(baseDir, "replays").apply { mkdirs() }

    fun newSegmentFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(Date())
        return File(segmentsDir, "segment_$timestamp.mp4")
    }

    fun newReplayFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        return File(replaysDir, "replay_$timestamp.mp4")
    }

    fun listReplays(): List<File> {
        return replaysDir.listFiles()?.sortedByDescending { it.lastModified() } ?: emptyList()
    }

    fun deleteSegment(file: File) {
        if (file.exists()) file.delete()
    }
}
