package com.sandeep.nspiretask.di

import android.app.Application
import android.content.Context
import com.sandeep.nspiretask.MyApplication
import com.sandeep.nspiretask.views.SearchRepoActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class, NetworkModule::class, RoomDatabaseModule::class , ViewModelModule::class])
interface ApplicationComponent : AndroidInjector<MyApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }
}