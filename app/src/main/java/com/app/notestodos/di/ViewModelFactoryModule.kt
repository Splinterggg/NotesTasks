package com.app.notestodos.di

import androidx.lifecycle.ViewModelProvider
import com.app.notestodos.MyViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    // Method #2
    @Binds
    abstract fun bindViewModelFactory(viewModelProvideFactory: MyViewModelFactory): ViewModelProvider.Factory
}