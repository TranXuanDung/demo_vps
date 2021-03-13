package com.dungtx.mvvm.common.eventbus

interface ActionBus<Data> :BaseAction {
    fun call(data: Data)
}