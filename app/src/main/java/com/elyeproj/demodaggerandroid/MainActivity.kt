package com.elyeproj.demodaggerandroid

import android.os.Bundle
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject lateinit var info : Info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        my_text.text = info.text
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment()).commit()
    }
}

class Info {
    val text = "Hello Dagger Android"
}

@Module
class MainActivityModule {
    @Provides
    @ActivityScope
    fun getInfo() = Info()
}
