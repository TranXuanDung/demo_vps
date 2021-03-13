package com.dungtx.mvvm.ui.main.home

import android.os.Bundle
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dungtx.mvvm.R
import com.dungtx.mvvm.data.model.home.Banner
import com.dungtx.mvvm.databinding.FragmentBannerBinding
import com.dungtx.mvvm.ui.base.fragment.BaseFragment
import com.dungtx.mvvm.ui.customview.GlideApp

class BannerFragment: BaseFragment() {


    var banner: Banner? = null

    companion object {
        private var ARGS_BANNER = "ARGS_BANNER"
        fun newsInstance(banner: Banner?): BannerFragment? {
            val bannerFragment = BannerFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARGS_BANNER, banner)
            bannerFragment.arguments = bundle
            return bannerFragment
        }
    }

    override fun getLayoutMain() = R.layout.fragment_banner

    override fun setEvents() {
    }

    override fun initComponents() {
        val arguments = arguments
        if (arguments != null) {
            banner = arguments.getParcelable(ARGS_BANNER)
            GlideApp.with(this)
                    .load(banner!!.image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(getBindingData().imgBanner)
        }
    }

    private fun getBindingData() = mBinding as FragmentBannerBinding
}