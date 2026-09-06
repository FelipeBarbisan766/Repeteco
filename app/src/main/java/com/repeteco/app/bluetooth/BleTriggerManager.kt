package com.repeteco.app.bluetooth

// PHASE 5: only needed if the shutter is NOT a standard HID device
// (most cheap BT shutters emulate a volume/media key, captured in
// MainActivity.onKeyDown instead of via GATT)
class BleTriggerManager(
    private val onTriggerPressed: () -> Unit
) {

    fun connectAndListen(deviceAddress: String) {
        // TODO: GATT connection via no.nordicsemi.android:ble
    }

    fun disconnect() {
        // TODO: safe disconnect
    }
}
