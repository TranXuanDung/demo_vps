package com.dungtx.mvvm.ui.customview.widget

import androidx.viewpager.widget.ViewPager

class CircularViewPagerHandler: ViewPager.OnPageChangeListener {

    private var mViewPager: ViewPager? = null
    private var mListener: ViewPager.OnPageChangeListener? = null

    constructor(vpBanner: ViewPager){
        this.mViewPager = vpBanner
        mViewPager!!.setCurrentItem(1, false)
    }

    fun setOnPageChangeListener(listener: ViewPager.OnPageChangeListener) {
        mListener = listener
    }

    override fun onPageScrollStateChanged(state: Int) {
        invokeOnPageScrollStateChanged(state)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        invokeOnPageScrolled(position, positionOffset, positionOffsetPixels)
    }

    override fun onPageSelected(position: Int) {
        handleSetCurrentItemWithDelay(position)
        invokeOnPageSelected(position)
    }

    private fun handleSetCurrentItemWithDelay(position: Int) {
        mViewPager!!.postDelayed({ handleSetCurrentItem(position) }, 300)
    }

    private fun handleSetCurrentItem(position: Int) {
        val lastPosition = mViewPager!!.adapter!!.count - 1
        if (position == 0) {
            mViewPager!!.setCurrentItem(lastPosition - 1, false)
        } else if (position == lastPosition) {
            mViewPager!!.setCurrentItem(1, false)
        }
    }

    private fun invokeOnPageSelected(position: Int) {
        if (mListener != null) {
            mListener!!.onPageSelected(position - 1)
        }
    }

    private fun invokeOnPageScrollStateChanged(state: Int) {
        if (mListener != null) {
            mListener!!.onPageScrollStateChanged(state)
        }
    }

    private fun invokeOnPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (mListener != null) {
            mListener!!.onPageScrolled(position - 1, positionOffset, positionOffsetPixels)
        }
    }
}