package com.cleanarch.codewars.demo.android

import android.app.Application
import com.cleanarch.codewars.demo.di.AppComponent
import com.cleanarch.codewars.demo.di.DaggerAppComponent

open class CodeWarsApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = createDaggerComponent()
    }

    protected open fun createDaggerComponent(): AppComponent {
        val component = DaggerAppComponent.builder()
            .application(this)
            .build()
        component.inject(this)
        return component
    }

}