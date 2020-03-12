package com.app.notestodos.di

import com.app.notestodos.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun bindMainActivity(): MainActivity
}