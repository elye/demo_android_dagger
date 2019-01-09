package com.elyeproj.demodaggerandroid

import android.app.Activity
import android.app.Application
import dagger.Component
import dagger.Module
import dagger.android.*
import javax.inject.Inject


class MainApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .create()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}

@Component(modules = [AndroidInjectionModule::class, ActivityBuilder::class])
interface AppComponent {
    fun inject(app: MainApplication)
}

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}
