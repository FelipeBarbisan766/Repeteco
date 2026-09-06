package com.repeteco.app.bluetooth

class BleDeviceScanner(
    private val onDeviceFound: (name: String?, address: String) -> Unit
) {

    fun startScan() {
        // TODO: BluetoothLeScanner or Nordic BLE lib
    }

    fun stopScan() {
        // TODO: stop scan
    }
}
