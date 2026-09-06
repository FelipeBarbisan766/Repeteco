package com.repeteco.app.processing

import java.io.File

// PHASE 4: concat segments via FFmpeg concat demuxer (remux, no reencode)
// ffmpeg -f concat -safe 0 -i concat_list.txt -c copy replay_final.mp4
class FFmpegProcessor {

    private fun buildConcatListFile(segments: List<File>, outputDir: File): File {
        // TODO: write .txt with "file 'path/to/segment.mp4'" lines
        throw NotImplementedError()
    }

    fun concatenateSegments(
        segments: List<File>,
        outputFile: File,
        onComplete: (success: Boolean, error: String?) -> Unit
    ) {
        // TODO: buildConcatListFile -> FFmpegKit.executeAsync(...) -> onComplete
    }
}
