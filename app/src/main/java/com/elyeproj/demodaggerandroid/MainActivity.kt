package com.elyeproj.demodaggerandroid

import android.os.Bundle
import dagger.Module
import dagger.Provides
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerActivity() {

    @Inject lateinit var info : Info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
