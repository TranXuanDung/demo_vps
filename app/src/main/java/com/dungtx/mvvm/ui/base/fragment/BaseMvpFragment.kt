package com.dungtx.mvvm.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungtx.mvvm.BR
import com.dungtx.mvvm.ui.base.model.BaseViewModel
import com.dungtx.mvvm.ui.base.callback.BaseCallBack
import java.util.*

abstract class BaseMvpFragment<
        CallBack : BaseCallBack,
        Model : BaseViewModel<CallBack>
        > : BaseFragment() {
    protected var mFirstLoad: Long = 0
    protected lateinit var mModel: Model
    override fun onCreateViewControl(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = super.onCreateViewControl(inflater, container, savedInstanceState)
        mModel = createModel()
        return view

    }

    override fun onViewCreatedControl(view: View, savedInstanceState: Bundle?) {
        mFirstLoad = Date().time
        super.onViewCreatedControl(view, savedInstanceState)
    }

    abstract fun createModel(): Model
}