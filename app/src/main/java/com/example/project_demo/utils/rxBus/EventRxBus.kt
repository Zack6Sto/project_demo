package com.example.project_demo.utils.rxBus

object EventRxBus {

    fun <RequestType> onAddEventRxBus(rxClass: RequestType) {
        RxBus.publish(rxClass)
    }
}