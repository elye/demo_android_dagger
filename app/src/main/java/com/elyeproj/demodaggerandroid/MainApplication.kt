package com.elyeproj.demodaggerandroid

import android.app.Activity
import android.app.Application
import android.content.Context
import dagger.*
import dagger.android.*
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
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

@Module(subcomponents = [MainActivityComponent::class])
abstract class ActivityBuilder {
    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    abstract fun bindMainActivity(builder: MainActivityComponent.Builder): AndroidInjector.Factory<*>
}
