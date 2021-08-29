package com.sandeep.nspiretask.di

import com.sandeep.nspiretask.views.RepoDetails
import com.sandeep.nspiretask.views.SearchRepoActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSearchRepoActivity(): SearchRepoActivity

    @ContributesAndroidInjector
    abstract fun contributeRepoDetails(): RepoDetails
}