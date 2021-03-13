package com.dungtx.mvvm.ui.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dungtx.mvvm.data.model.home.Banner
import com.dungtx.mvvm.ui.base.adapter.BaseCircularViewPagerAdapter

class BannerAdapter(fm: FragmentManager, banners: List<Banner>) : BaseCircularViewPagerAdapter<Banner>(fm, banners) {

    override fun getFragmentForItem(banner: Banner): Fragment? {
        return BannerFragment.newsInstance(banner)
    }
}