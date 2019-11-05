package com.elyeproj.demodaggerandroid

import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Scope

class MainApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<MainApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}

@Component(modules = [AndroidSupportInjectionModule::class, AndroidInjectBuilder::class])
interface AppComponent: AndroidInjector<MainApplication> {
    @Component.Factory
    interface Factory: AndroidInjector.Factory<MainApplication>
}

@Module
abstract class AndroidInjectBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @FragmentScope
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun contributeMainFragment(): MainFragment
}


@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope