package com.dungtx.mvvm.di.model

import android.app.Application
import android.content.Context
import com.dungtx.mvvm.data.remote.InteractCommon
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AppModel {
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideInteractCommon(): InteractCommon {
        return InteractCommon()
    }

    @Provides
    @Singleton
    internal fun provideSchedule(): Executor {
        return Executors.newFixedThreadPool(4)
    }
}