package com.elyeproj.demodaggerandroid

import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication


class MainApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<MainApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}

@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilder::class])
interface AppComponent: AndroidInjector<MainApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MainApplication): Builder
        fun build(): AppComponent
    }
}

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}
