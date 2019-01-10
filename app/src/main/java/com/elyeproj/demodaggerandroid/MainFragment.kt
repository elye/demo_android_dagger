package com.elyeproj.demodaggerandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment: DaggerFragment() {
    @Inject
    lateinit var info: FragmentInfo

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_text.text = info.text
    }
}

class FragmentInfo {
    val text = "Hello Dagger Fragment"
}

@Module
class MainFragmentModule {
    @Provides
    @FragmentScope
    fun getFragmentInfo() = FragmentInfo()
}
