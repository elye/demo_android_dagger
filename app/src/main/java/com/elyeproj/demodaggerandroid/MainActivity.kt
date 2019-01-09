package com.elyeproj.demodaggerandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dagger.*
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var info : Info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        my_text.text = info.text
    }
}

class Info {
    val text = "Hello Dagger Android"
}

@Module
class MainActivityModule {
    @Provides
    fun getInfo() = Info()
}

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
