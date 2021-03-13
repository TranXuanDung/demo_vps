package com.dungtx.mvvm.ui.utils

import androidx.fragment.app.FragmentManager
import com.dungtx.mvvm.R
import com.dungtx.mvvm.ui.base.AnimationScreen
import com.dungtx.mvvm.ui.base.fragment.BaseFragment

object OpenFragmentUtils {
    @JvmStatic
    fun getAnimationScreenFullOpen(): AnimationScreen {
        return AnimationScreen(R.anim.enter_to_left, R.anim.exit_to_left, R.anim.enter_to_right, R.anim.exit_to_right)
    }
}