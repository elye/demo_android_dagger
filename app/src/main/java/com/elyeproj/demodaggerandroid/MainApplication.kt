package com.elyeproj.demodaggerandroid

import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Scope


class MainApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<MainApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}

@Component(modules = [AndroidSupportInjectionModule::class, AndroidInjectBuilder::class])
interface AppComponent: AndroidInjector<MainApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MainApplication): Builder
        fun build(): AppComponent
    }
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