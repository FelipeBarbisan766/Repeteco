package com.repeteco.app.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {

    private val requiredPermissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
    )

    private var hasPermissions by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hasPermissions = hasRequiredPermissions()

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            hasPermissions = result.values.all { it }
        }

        if (!hasPermissions) {
            permissionLauncher.launch(requiredPermissions)
        }

        setContent {
            if (hasPermissions) {
                CameraPreviewScreen()
            }
        }
    }

    private fun hasRequiredPermissions(): Boolean {
        return requiredPermissions.all {
            checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
        }
    }

    // PHASE 5: BT shutter as HID key event
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // TODO: if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) triggerReplay()
        return super.onKeyDown(keyCode, event)
    }
}
