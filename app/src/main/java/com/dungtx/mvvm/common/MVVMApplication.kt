package com.dungtx.mvvm.common

import android.content.Context
import android.net.Uri
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.dungtx.mvvm.di.compoment.AppComponent
import com.dungtx.mvvm.di.compoment.DaggerAppComponent

class MVVMApplication : MultiDexApplication() {
    lateinit var appComponent: AppComponent
        get

    companion object{
        var listUri: MutableList<Uri> = mutableListOf()
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)

    }

    fun interactCommon() = appComponent.interactCommon()
    fun schedule() = appComponent.schedule()

}