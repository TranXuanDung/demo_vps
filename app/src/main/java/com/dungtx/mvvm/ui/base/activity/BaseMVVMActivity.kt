package com.dungtx.mvvm.ui.base.activity

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import com.dungtx.mvvm.BR
import com.dungtx.mvvm.common.Constants
import com.dungtx.mvvm.ui.base.model.BaseViewModel
import com.dungtx.mvvm.ui.base.callback.BaseCallBack
import java.util.*

abstract class BaseMVVMActivity<
        CallBack : BaseCallBack,
        Model : BaseViewModel<CallBack>> : BaseActivity() {
    protected lateinit var mModel: Model
    protected var mFirstLoad: Long = 0

    override fun onCreateControl(savedInstanceState: Bundle?) {
        if (!mIsClearMemoryActivity) {
            mFirstLoad = Date().time
            mBinding = DataBindingUtil.setContentView(this, getLayoutMain())
            mModel = createModel()
            performDataBinding()
            setEvents()
            initComponents()
        }
        super.onCreateControl(savedInstanceState)
    }


    abstract fun createModel(): Model
    private fun performDataBinding() {
        mBinding.executePendingBindings()
    }

    protected fun <T> finishLoad(t: T, action: (T) -> Unit) {
        if (mIsDestroyView) {
            return
        }
        if (mFirstLoad == (-1).toLong()) {
            action(t)
        } else {
            val currentTime = Date().time
            if (currentTime - mFirstLoad >= Constants.DURATION_ANIMATION) {
                action(t)
            } else {
                Handler().postDelayed({
                    if (mIsDestroyView) {
                        return@postDelayed
                    }
                    action(t)
                }, Constants.DURATION_ANIMATION - (currentTime - mFirstLoad))
            }
            mFirstLoad = -1
        }
    }


}