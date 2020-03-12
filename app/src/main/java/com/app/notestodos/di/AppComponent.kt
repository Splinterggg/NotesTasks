package com.app.notestodos.di

import android.app.Application
import com.app.notestodos.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(modules = [AppModule::class,
    AndroidInjectionModule::class,
    ActivityModule::class,
ViewModelFactoryModule::class])
@Singleton
interface AppComponent : AndroidInjector<MainApplication>{
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
