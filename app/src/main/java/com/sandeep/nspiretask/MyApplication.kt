package com.sandeep.nspiretask

import com.sandeep.nspiretask.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return  DaggerApplicationComponent.factory().create(this)
    }

}