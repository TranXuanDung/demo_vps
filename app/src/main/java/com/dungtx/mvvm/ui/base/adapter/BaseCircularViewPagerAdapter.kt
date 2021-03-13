package com.dungtx.mvvm.ui.base.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

abstract class BaseCircularViewPagerAdapter<T>(fm: FragmentManager, private var mItems: List<T>) : FragmentPagerAdapter(fm) {

    protected abstract fun getFragmentForItem(item: T): Fragment?

    override fun getItem(position: Int): Fragment {
        val itemsSize = mItems.size
        return if (position == 0) {
            getFragmentForItem(mItems[itemsSize - 1])!!
        } else if (position == itemsSize + 1) {
            getFragmentForItem(mItems[0])!!
        } else {
            getFragmentForItem(mItems[position - 1])!!
        }
    }

    override fun getCount(): Int {
        val itemsSize = mItems.size
        return if (itemsSize > 1) itemsSize + 2 else itemsSize
    }

    open fun setItems(items: List<T>) {
        mItems = items
        notifyDataSetChanged()
    }
}