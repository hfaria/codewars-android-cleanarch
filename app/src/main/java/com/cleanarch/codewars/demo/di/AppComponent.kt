package com.cleanarch.codewars.demo.di

import android.app.Application
import com.cleanarch.codewars.demo.android.CodeWarsApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DataLayerModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(codeWarsApp: CodeWarsApp)

}
