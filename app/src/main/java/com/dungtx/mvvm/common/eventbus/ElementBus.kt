package com.dungtx.realmmvp.eventbus

import com.dungtx.mvvm.common.eventbus.BaseAction


/**
 * Created by ducnd on 8/18/17.
 */
class ElementBus(id: String) {
    val listAction: MutableList<BaseAction> = mutableListOf()
    val id: String = id
}