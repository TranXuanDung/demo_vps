package com.dungtx.mvvm.ui.base.callback

import com.dungtx.mvvm.ui.base.BaseViewUI

interface BaseCallBack : BaseViewUI{
    fun error(id: String, error: Throwable)
}