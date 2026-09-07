package com.repeteco.app.camera

import java.io.File
import java.util.LinkedList

// PHASE 3: rolling window of segment files
class CircularBufferManager(
    private val windowDurationSeconds: Int = 40,
    private val segmentDurationSeconds: Int = 10
) {

    private val segments = LinkedList<File>()

    private val maxSegments: Int
        get() = windowDurationSeconds / segmentDurationSeconds

    @Synchronized
    fun addSegment(segment: File) {
        segments.addLast(segment)
        while (segments.size > maxSegments) {
            val oldest = segments.removeFirst()
            deleteSafely(oldest)
        }
    }

    @Synchronized
    fun getCurrentSegments(): List<File> = segments.toList()

    @Synchronized
    fun clear() {
        segments.forEach { deleteSafely(it) }
        segments.clear()
    }

    private fun deleteSafely(file: File) {
        if (file.exists() && !file.delete()) {
            file.deleteOnExit()
        }
    }
}
