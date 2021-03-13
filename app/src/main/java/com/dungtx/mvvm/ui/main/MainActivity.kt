package com.dungtx.mvvm.ui.main

import android.view.MenuItem
import android.view.View
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.dungtx.mvvm.R
import com.dungtx.mvvm.ui.base.activity.BaseActivity
import com.dungtx.mvvm.ui.main.asset.AssetFragment
import com.dungtx.mvvm.ui.main.call.CallFragment
import com.dungtx.mvvm.ui.main.finance.FinanceFragment
import com.dungtx.mvvm.ui.main.home.HomeFragment
import com.dungtx.mvvm.ui.main.stock.StockFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : BaseActivity() {

    override fun getLayoutMain() = R.layout.activity_main

    override fun setEvents() {
    }

    override fun initComponents() {
        val navigation = findViewById<View>(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadFragment(HomeFragment())
    }

    private val mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
            val fragment: Fragment
            when (item.itemId) {
                R.id.navigation_home -> {
                    fragment = HomeFragment()
                    loadFragment(fragment)
                    return true
                }
                R.id.navigation_stock -> {
                    fragment = StockFragment()
                    loadFragment(fragment)
                    return true
                }
                R.id.navigation_call -> {
                    fragment = CallFragment()
                    loadFragment(fragment)
                    return true
                }
                R.id.navigation_finance -> {
                    fragment = FinanceFragment()
                    loadFragment(fragment)
                    return true
                }
                R.id.navigation_asset -> {
                    fragment = AssetFragment()
                    loadFragment(fragment)
                    return true
                }
            }
            return false
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
