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

    fun addSegment(segment: File) {
        segments.addLast(segment)
        // TODO: if segments.size > maxSegments, remove + delete oldest
    }

    fun getCurrentSegments(): List<File> = segments.toList()

    fun clear() {
        // TODO: delete all files safely
        segments.clear()
    }
}
