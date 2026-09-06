package com.repeteco.app.ui

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // TODO: CameraPreviewScreen()
        }
    }

    // PHASE 5: BT shutter as HID key event
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // TODO: if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) triggerReplay()
        return super.onKeyDown(keyCode, event)
    }
}
