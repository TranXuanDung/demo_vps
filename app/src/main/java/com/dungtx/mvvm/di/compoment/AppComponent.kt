package com.dungtx.mvvm.di.compoment

import android.app.Application
import android.content.Context
import com.dungtx.mvvm.common.MVVMApplication
import com.dungtx.mvvm.data.remote.InteractCommon
import com.dungtx.mvvm.di.model.AppModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import java.util.concurrent.Executor
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModel::class])
interface AppComponent {
    fun inject(application: MVVMApplication)


    fun context(): Context
    fun interactCommon(): InteractCommon
    fun schedule(): Executor

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}