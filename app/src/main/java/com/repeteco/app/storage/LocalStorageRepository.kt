package com.repeteco.app.storage

import android.content.Context
import java.io.File

class LocalStorageRepository(
    private val context: Context
) {

    fun saveToInternalStorage(replayFile: File): File {
        // TODO: move to filesDir/replays
        return replayFile
    }

    fun saveToMediaStore(replayFile: File, displayName: String) {
        // TODO: ContentResolver + MediaStore insert
    }

    fun deleteReplay(replayFile: File) {
        if (replayFile.exists()) replayFile.delete()
    }
}
